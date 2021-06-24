package academy.mindswap.Game;

import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Team> createGroup = new ArrayList<>();


    public void ChooseTeam (String teamName){
        if (teamName.equals("Portugal")){
            Team team = TeamHandler.createTeam(new Team("Portugal"),TeamHandler.portugal);
            createGroup.add(team);
        }
        if (teamName.equals("Germany")){
            Team team = TeamHandler.createTeam(new Team("Germany"),TeamHandler.germany);
            createGroup.add(team);
        }
        if (teamName.equals("France")){
            Team team = TeamHandler.createTeam(new Team("France"),TeamHandler.france);
            createGroup.add(team);
        }
        if (teamName.equals("Hungry")){
            Team team = TeamHandler.createTeam(new Team("Hungary"),TeamHandler.hungary);
            createGroup.add(team);
        }

    }


}
