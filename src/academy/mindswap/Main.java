package academy.mindswap;

import academy.mindswap.Game.Game;
import academy.mindswap.Game.Match;
import academy.mindswap.players.Players;
import academy.mindswap.server.GameServer;
import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;
import academy.mindswap.training.Training;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
//		Team portugal = TeamHandler.createTeam(new Team("Portugal"), TeamHandler.portugal);
////		System.out.println(portugal.getName());
////		portugal.printPlayerList();
//		Players feliz = portugal.choosePlayer("Joao Felix");
//		System.out.println(feliz.getOverall());
//		Training.developPlayers(feliz);
//		System.out.println(feliz.getOverall());

//		try {
//			GameServer server = new GameServer();
//			server.start(8080);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Team portugal = TeamHandler.createTeam(new Team ("Portugal"), TeamHandler.portugal);
		Team france = TeamHandler.createTeam(new Team ("France"), TeamHandler.france);
		Team hungary = TeamHandler.createTeam(new Team ("Hungary"), TeamHandler.hungary);
		Team germany = TeamHandler.createTeam(new Team ("Germany"), TeamHandler.germany);

		Game game = new Game();
		game.play();








	}
}
