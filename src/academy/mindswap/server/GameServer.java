package academy.mindswap.server;

import academy.mindswap.game.Game;
import academy.mindswap.players.Player;
import academy.mindswap.server.messages.ServerMessages;
import academy.mindswap.teams.Team;
import academy.mindswap.training.Training;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {
	private ServerSocket serverSocket;
	private ExecutorService service;
	private final List<ClientHandler> clients;
	private Set<String> usernames;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * this method creates a linked list of clients and a hash set of usernames
	 */
	public GameServer() {
		clients = new LinkedList<>();
		usernames = new HashSet<>();
	}

	/**
	 * This method opens a socket
	 * @param port to which the client will connect
	 * @throws IOException
	 */
	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		service = Executors.newCachedThreadPool();
		while (true) {
			Socket clientSocket = serverSocket.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			out.println(ServerMessages.WELCOME_SCREEN);
			String username = chooseUserName();

			service.submit(new ClientHandler(clientSocket, username));
		}
	}

	private String chooseUserName() throws IOException {
		out.println(ServerMessages.CHOOSE_USERNAME);
		String username = in.readLine();
		if (!(isUserNameAvailable(username))) {
			chooseUserName();
		} else {
			usernames.add(username);
		}
		return username;
	}

	private boolean isUserNameAvailable(String username) {
		if (!(usernames.add(username))) {
			out.println(username + ServerMessages.UNAVAILABLE_USERNAME);
			return false;
		} else {
			out.println(ServerMessages.WELCOME_USERNAME + username);
			return true;
		}
	}

	public class ClientHandler implements Runnable {
		private String username;
		private Team team;
		private Socket clientSocket;
		private PrintWriter out;
		private String command;
		private Game game;
		private boolean isInFinal;

		public ClientHandler(Socket clientSocket, String username) throws IOException {
			this.username = username;
			this.clientSocket = clientSocket;
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				printCommands();
				while (!(clientSocket.isClosed())) {
					command = in.readLine();
					if (command.equals("")) {
						continue;
					}

					dealWithCommand(command);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void dealWithCommand(String command) throws IOException {
			switch (command.toLowerCase().replace("/", "")) {
				case "play":
					play();
					break;
				case "help":
					printCommands();
					break;
				case "teams":
					printTeams();
					break;
				case "lineup":
					printLineUp();
					break;
				case "train":
					trainPlayer();
					break;
				case "skip":
					skipDay();
					break;
				case "timeline":
					printTimeLine();
					break;
				case "upcoming":
					printUpComingMatches();
				case "match":
					playMatch();
					break;
				default:
					out.println(ServerMessages.INVALID_COMMAND);
					printCommands();
					break;
			}
		}

		private void play() throws IOException {
			game = new Game(out, in);
			printTeams();
			out.println(ServerMessages.CHOOSE_TEAM);
			boolean invalidTeam = false;
			while (!invalidTeam) {
				String teamName = in.readLine();
				team = game.chooseTeam(teamName.toLowerCase());
				if (!(isValidTeam(team))) {
					out.println(ServerMessages.INVALID_TEAM);
				} else {
					invalidTeam = true;
				}
			}
			printCommands();
		}

		private boolean isValidTeam(Team team) {
			return team != null;
		}

		private void printCommands() {
			out.println(ServerMessages.COMMANDS_LIST);
		}

		private void printTeams() {
			if (game == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			out.println(ServerMessages.AVAILABLE_TEAMS + game.printTeams());
		}

		private void printLineUp() {
			if (team == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			out.println("\n" + team.getName() + ServerMessages.LINEUP);
			out.println(game.printLineUp());
		}

		private void trainPlayer() throws IOException {
			if (team == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			if (game.getDaysUntilMatch() < 1) {
				out.println(ServerMessages.MATCH_DAY);
				return;
			}
			printLineUp();
			out.println(ServerMessages.TRAINING_TARGET);
			Player player = null;
			String playerName = in.readLine();
			player = team.choosePlayer(playerName);
			if (player == null) {
				out.println(ServerMessages.INVALID_PLAYER);
				return;
			}
			out.println(Training.developPlayers(player));
			game.passDay();
		}

		private void skipDay() {
			if (team == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			if (game.getDaysUntilMatch() == 0) {
				out.println(ServerMessages.MATCH_DAY);
				return;
			}
			game.passDay();
			out.println(game.getDaysUntilMatch() + ServerMessages.DAYS_UNTIL_MATCH);
		}

		private void printTimeLine() {
			if (team == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			if (game.getDaysUntilMatch() > 0) {
				out.println(game.getDaysUntilMatch() + ServerMessages.DAYS_UNTIL_MATCH);
				return;
			}
			if (game.getDaysUntilMatch() == 0) {
				out.println(ServerMessages.MATCH_DAY);
			}
		}

		private void printUpComingMatches() {
			if (team == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			if(isInFinal){
				out.println(game.getFinalMatch());
				return;
			}
			out.println(game.printMatches());
		}

		private void playMatch() throws IOException {
			if (team == null) {
				out.println(ServerMessages.INVALID_START);
				return;
			}
			if (game.getDaysUntilMatch() > 0) {
				out.println(ServerMessages.NOT_MATCH_DAY);
				out.println(game.getDaysUntilMatch() + ServerMessages.DAYS_UNTIL_MATCH);
				return;
			}
			if (isInFinal) {
				if(game.playFinal()){
					run();
				} else {
					out.println(ServerMessages.CLOSING_GAME);
					clientSocket.close();
				}
			} else {
				if (game.play()) {
					isInFinal = true;
					printTimeLine();
					printCommands();
				} else {
					run();
				}
			}
		}
	}
}
