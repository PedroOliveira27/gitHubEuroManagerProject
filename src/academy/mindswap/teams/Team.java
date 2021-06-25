package academy.mindswap.teams;

import academy.mindswap.players.Players;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private String name;
	private List<Players> team;

	public Team(String name) {
		this.name = name;
		team = new ArrayList<>();
	}

	public void printPlayerList() {
		System.out.println("Starting team for " + name + ":");
		System.out.println(team.size() + " players.");
		for (Players p : team) {
			System.out.println(p.getName() + " | " +
					p.getOverall() + " | " +
					p.getPositionType());
		}
	}

	public void addPlayer(List<Players> players) {
		for(Players p : players){
			team.add(p);
		}
	}

	public Players choosePlayer(String playerName) {
		for(Players p : team){
			if (p.getName().equals(playerName)){
				return p;
			}
		}
		return null;
	}
	public Players choosePlayer(int playerIndex) {

		return team.get(playerIndex);
	}


	public int getOverall() {
		int overall = 0;
		for (Players p : team) {
			overall += p.getOverall();
		}
		return overall / team.size();
	}

	public String getName() {
		return name;
	}
}
