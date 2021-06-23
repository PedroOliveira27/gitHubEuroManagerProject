package academy.mindswap.teams;

import academy.mindswap.Players;

import java.util.HashSet;
import java.util.Set;

public class Team {
	private Set<Players> team;

	public Team() {
		team = new HashSet<>();
	}

	public void printPlayerList(){
		for (Players p : team) {
			System.out.print(p.getName() + " | " +
					p.getOverall() + " | " +
					p.getPositionType());
		}
	}

	public int getOverall() {
		int overall = 0;
		for (Players p : team) {
			overall += p.getOverall();
		}
		return overall/team.size();
	}
}
