package academy.mindswap.Game;

import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Groups {
    List<Team> createGroup = new ArrayList<>();
    List<String> availableTeams = new LinkedList<>();
    List<Team> winnerTeams = new LinkedList<>();


    public void ChooseTeam(String teamName) {
        if (teamName.equals("Portugal")) {
            Team team = TeamHandler.createTeam(new Team("Portugal"), TeamHandler.portugal);
            createGroup.add(team);
            removeTeams(teamName);

        }
        if (teamName.equals("Germany")) {
            Team team = TeamHandler.createTeam(new Team("Germany"), TeamHandler.germany);
            createGroup.add(team);
            removeTeams(teamName);
        }
        if (teamName.equals("France")) {
            Team team = TeamHandler.createTeam(new Team("France"), TeamHandler.france);
            createGroup.add(team);
            removeTeams(teamName);
        }
        if (teamName.equals("Hungary")) {
            Team team = TeamHandler.createTeam(new Team("Hungary"), TeamHandler.hungary);
            createGroup.add(team);
            removeTeams(teamName);
        }

    }

    public void removeTeams(String teamName) {
        for (String team : availableTeams) {
            if (team.equals(teamName)) {
                availableTeams.remove(teamName);
                return;
            }

        }

    }

    public String teamsAvailable() {
        String teamList = "Available teams: \n";
        for (String team : availableTeams) {
            teamList += team + "\n";
        }
        return teamList;


    }
//
////    public void teamsThatWon(Team team) {
////        winnerTeams.add(team);
////
//
//    }



    public void addTeams(String team) {
        availableTeams.add(team);
    }
}
