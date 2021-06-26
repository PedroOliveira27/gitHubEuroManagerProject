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


    public Team RandomGame() {
        int max = 3;
        int min = 0;
        Team firstTeam;
        int FirstTeamPlay = (int) (Math.random() * (max - min + 1)) + min;
        firstTeam = group.createGroup.get(FirstTeamPlay);
        return firstTeam;
    }

    public void play() {
        group.ChooseTeam("Portugal");
        group.ChooseTeam("France");
        group.ChooseTeam("Hungary");
        group.ChooseTeam("Germany");
        Team team1 = null;
        Team team2 = null;
        Team team3 = null;
        Team team4 = null;
        team1 = randomTeam(team1);
//        System.out.println(team1.getName());
        team2 = randomTeam(team1);
//        System.out.println(team2.getName());
        team3 = randomTeam(team1);
//        System.out.println(team3.getName());
        team4 = randomTeam(team1);
//        System.out.println(team4.getName());
        if(team1 != team2 && team1 != team3 && team1 != team4 && team2 != team3 && team2 != team4 && team3 != team4) {  // alterado sujeito a aprovaçao de Angelo
            Match match = new Match(team1, team2);
            Match match1 = new Match(team3, team4);
            match.start();
            match1.start();
            Match match2 = new Match(match.getWinTeamList().get(0), match1.getWinTeamList().get(0));
            System.out.println("Welcome to the final!!");
            match2.start();
            System.out.println(match2.getWinTeamList().get(0).getName() + "  won Euro 2020!!!!!! Congratulations to the champions");
            return;

        }
        play(); //alterado sujeito a aprovaçao de Angelo

    }

    public Team randomTeam(Team team) {
        Team team1 = RandomGame();
        while (team1 == team) {
            team1 = RandomGame();
        }
        return team1;
    }


//
//    public void knockoutStages(Match match2) {
//      match2 = new Match(match2.getWinTeamList().get(0), match2.getWinTeamList().get(1));
//
//        match2.start();
//
//
//    }


}
