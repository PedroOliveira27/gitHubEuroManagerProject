package academy.mindswap.Game;

import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Groups group;

    public Game() {
       group = new Groups();
    }
    public Game(Groups group) {
        this.group = group;
    }


    public Team RandomGame (){
        int max=3;
        int min=0;
        Team firstTeam;
        int FirstTeamPlay = (int) (Math.random() * (max-min+1))+min;
        firstTeam = group.createGroup.get(FirstTeamPlay);
       return firstTeam;
    }

    public void play(){
        group.ChooseTeam("Portugal");
        group.ChooseTeam("France");
        group.ChooseTeam("Hungary");
        group.ChooseTeam("Germany");
        Team team1 = RandomGame();
        Team team2 = RandomGame();
        Match match = new Match(team1, team2);
        match.start();
    }

}
