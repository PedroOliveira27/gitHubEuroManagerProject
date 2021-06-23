package academy.mindswap.teams;

import academy.mindswap.Players;

import java.util.HashSet;
import java.util.Set;

public class Team {
	private Set<Players> team;
	private int teamOverall;

	public Team() {
		team = new HashSet<>();
	}

	private int getOverall() {
		int overall = 0;
		for (Players p : team) {
			overall += p.getOverall();
		}
		return overall/team.size();
	}
}
