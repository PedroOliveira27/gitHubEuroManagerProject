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
     * this method creates a linked list of clients and a hash set of usernames,
     * so the client's can't have the same username
     */
    public GameServer() {
        clients = new LinkedList<>();
        usernames = new HashSet<>();
    }

    /**
     * This method opens a socket which will be listening to connections.
     * Every time a connection is made, the server will ask the client a prefered
     * username. After getting a valid username, a new thread is created to deal with
     * that connection.
     *
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

    /**
     * This method asks the client for a username, and then checks
     * if it is a valid username. If it's valid, the client's chosen username
     * will be added to the username list, so no other client can use it. If
     * it's not valid the client will be asked to choose another username.
     *
     * @return the username when valid
     * @throws IOException
     */
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

    /**
     * This method checks if the username chosen by the client
     * is available. In case it is, the client will be greeted with
     * a welcome message. In case it is not valid, the client will be
     * warned with an unavailable username message.
     *
     * @param username - String username chosen by the client
     * @return true - if username is valid
     * false - if username is not valid
     */
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

        /**
         * This method creates an input stream using the client socket,
         * and reads the client's input so they can be dealt with,
         * in case they are valid commands.
         */
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

        /**
         * This method deals with all the commands given by the user.
         * If the command is valid, it will proceed to do the functionality behind it.
         * If the command is not valid, a messages will be printed advising the client,
         * followed by the list of available commands.
         *
         * @param command - String command given by the user
         * @throws IOException
         */
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

        /**
         * This method deals with command play.
         * It instantiates a new game, and gives the client
         * a team option.
         * After choosing the team, the client is presented with
         * a list of available commands
         *
         * @throws IOException
         */
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

        /**
         * This methods checks if the team has been chosen already.
         *
         * @param team
         * @return
         */
        private boolean isValidTeam(Team team) {
            return team != null;
        }

        /**
         * This method deals with command help.
         * It prints a message with all the available commands.
         */
        private void printCommands() {
            out.println(ServerMessages.COMMANDS_LIST);
        }

        /**
         * This method deals with command teams.
         * If the game has been started, it will print
         * the available teams.
         */
        private void printTeams() {
            if (game == null) {
                out.println(ServerMessages.INVALID_START);
                return;
            }
            out.println(ServerMessages.AVAILABLE_TEAMS + game.printTeams());
        }

        /**
         * This method deals with command lineup.
         * If the game has been started, the chosen team's
         * lineup will be printed.
         */
        private void printLineUp() {
            if (team == null) {
                out.println(ServerMessages.INVALID_START);
                return;
            }
            out.println("\n" + team.getName() + ServerMessages.LINEUP);
            out.println(game.printLineUp());
        }

        /**
         * This method deals with command train.
         * If the game has been started and it's not match day,
         * the player can train a player in order to increase his overall,
         * given that the player name is a valid one (matches the name of a
         * player in the lineup of the team).
         * After training the player, a day passes.
         *
         * @throws IOException
         */
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

        /**
         * This method deals with command skip.
         * If the game has been started and it's not match day,
         * a day will pass.
         * If it's match day, it will print a message saying that
         * it is match day.
         */
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

        /**
         * This method deals with command timeline.
         * If the game has been started and it's not match day,
         * it will print how many days until match day.
         * If it's match day, it will print a message saying that
         * it is match day.
         */
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

        /**
         * This method deals with command upcoming.
         * If the game has been started, it will print the upcoming match.
         * If the client's team is in the finals, then it will print the teams
         * playing in the finals.
         */
        private void printUpComingMatches() {
            if (team == null) {
                out.println(ServerMessages.INVALID_START);
                return;
            }
            if (isInFinal) {
                out.println(game.getFinalMatch());
                return;
            }
            out.println(game.printMatches());
        }

        /**
         * This method deals with command match.
         * If the game has been started and it's match day,
         * then the match will be played with the client team.
         *
         * @throws IOException
         */
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
                if (game.playFinal()) {
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
