package academy.mindswap.teams;

import academy.mindswap.players.Players;

public class TeamHandler {

	public void createTeam(Team team, Players player) {
		team.addPlayer(player);
	}
}
