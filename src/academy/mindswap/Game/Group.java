package academy.mindswap.Game;

import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;

import java.util.LinkedList;
import java.util.List;

public class Group {
	List<Team> availableTeams = new LinkedList<>();

	public Group() {
		Team team = TeamHandler.createTeam(new Team("Portugal"), TeamHandler.portugal);
		availableTeams.add(team);
		team = TeamHandler.createTeam(new Team("Germany"), TeamHandler.germany);
		availableTeams.add(team);
		team = TeamHandler.createTeam(new Team("France"), TeamHandler.france);
		availableTeams.add(team);
		team = TeamHandler.createTeam(new Team("Hungary"), TeamHandler.hungary);
		availableTeams.add(team);
	}

	public String printAvailableTeams() {
		String teamList = "";
		for (Team team : availableTeams) {
			teamList += team.getName() + "\n";
		}
		return teamList;
	}
}
