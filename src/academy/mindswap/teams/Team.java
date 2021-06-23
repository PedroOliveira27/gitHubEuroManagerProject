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
		for (Players p : team) {
			System.out.print(p.getName() + " | " +
					p.getOverall() + " | " +
					p.getPositionType());
		}
	}

	public void addPlayer(List<Players> players) {
		for(Players p : players){
			team.add(p);
		}
	}

	public int getOverall() {
		int overall = 0;
		for (Players p : team) {
			overall += p.getOverall();
		}
		return overall / team.size();
	}

	public Team(String name) {
		this.name = name;
	}
}
