package academy.mindswap;

import academy.mindswap.Game.Match;
import academy.mindswap.server.GameServer;
import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;
import academy.mindswap.training.Training;

import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	public static void main(String[] args) {
//		Team portugal = TeamHandler.createTeam(new Team("Portugal"), TeamHandler.portugal);
//		Team hungary = TeamHandler.createTeam(new Team("Hungary"), TeamHandler.hungary);
//		PrintWriter out = new PrintWriter(System.out);
//		Match match = new Match(portugal, portugal, out);
//		match.start();
		try {
			GameServer server = new GameServer();
			server.start(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
