package academy.mindswap.server;

import academy.mindswap.server.GameServer;
import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;
import academy.mindswap.training.Training;

import java.io.IOException;

public class ServerLauncher {
	/**
	 * This method instantiates a new Server. This server
	 * will then start and wait for connections.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GameServer server = new GameServer();
			server.start(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
