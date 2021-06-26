package academy.mindswap.server;

import academy.mindswap.Game.Game;
import academy.mindswap.server.messages.ServerMessages;
import academy.mindswap.teams.Team;

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


	public GameServer() {
		clients = new LinkedList<>();
		usernames = new HashSet<>();
	}

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
						return;
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
					game = new Game();
					out.println(ServerMessages.CHOOSE_TEAM);
					String team = in.readLine();
					game.ChooseTeam(team);
				case "help":
					printCommands();
					break;
				case "teams":
//					printTeams();
					break;
				case "lineup":
//					team.printPlayerList();
					break;
				default:
					out.println(ServerMessages.INVALID_COMMAND);
					printCommands();
					break;
			}
		}

		private void printCommands() {
			out.println(ServerMessages.COMMANDS_LIST);
		}

//		private void printTeams() {
//			out.println(game.printGroupTeams());
//		}

		public String getUsername() {
			return username;
		}

		public String getCommand() {
			return command;
		}
	}
}
