package academy.mindswap.server;

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
			out.println("""
					---------------------------------
					|    WELCOME TO EURO MANAGER    |
					---------------------------------
					""");
			String username = chooseUserName();
			service.submit(new ClientHandler(clientSocket, username));
		}
	}

	private String chooseUserName() throws IOException {
		out.println("Choose your username: ");
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
			out.println(username + " is not available. Choose another one.");
			return false;
		} else {
			out.println("Welcome, " + username);
			return true;
		}
	}

	public class ClientHandler implements Runnable {
		private String username;
		private Socket clientSocket;
		private PrintWriter out;
		private String command;

		public ClientHandler(Socket clientSocket, String username) throws IOException {
			this.username = username;
			this.clientSocket = clientSocket;
			this.out = new PrintWriter(clientSocket.getOutputStream(), true);
		}

		@Override
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				while (!(clientSocket.isClosed())){
					command = in.readLine();
					if(command.equals("")){
						return;
					}

					dealWithCommand(command);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void dealWithCommand(String command){

		}

		public String getUsername() {
			return username;
		}

		public String getCommand() {
			return command;
		}
	}
}
