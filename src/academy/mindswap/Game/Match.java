package academy.mindswap.Game;

import academy.mindswap.players.Players;
import academy.mindswap.players.Position;
import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Match {
    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;
    private List<Team> winTeamList = new LinkedList<>();
    private List<Team> loseTeamList = new LinkedList<>();
    private Position positionType;


    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    public Team start() {
        System.out.println("Match day!!!Welcome to the stadium!Today\n " +
                team1.getName() + " is playing against " + team2.getName());
        team1Score = 0;
        team2Score = 0;
        double matchTime = 0;

        Team teamWinner = play(90);

        if (teamWinner == null) {
            teamWinner = play(30);
        }
        if (team1Score != team2Score) {
            System.out.println("The game has ended!!The final score is \n" +
                    "[" + team1.getName() + " " + team1Score + "]" + " - " +
                    "[" + team2.getName() + " " + team2Score + "]");

        }
        return teamWinner;
    }

    private Team play(double maxMatchTime) {
        double currentmatchTime = 0;
        while (currentmatchTime < maxMatchTime) {
            Team team = aGoalIsScored();
            if (team == team1) {
                team1Score++;
                playersDidGoal(team1);
            }
            if (team == team2) {
                team2Score++;
                playersDidGoal(team2);
            }
            System.out.println("[" + team1.getName() + " " + team1Score + "]" + " - " +
                    "[" + team2.getName() + " " + team2Score + "]");
            if (maxMatchTime / 2 == currentmatchTime) {
                System.out.println("It's half time! Are you ready ? Let's go TEAM!");
            }
            currentmatchTime += (maxMatchTime / 4);
        }
        if (team1Score == team2Score) {
            if (maxMatchTime == 30) {
                System.out.println("Penalties!");
                penalties();

            } else if (maxMatchTime == 90) {
                System.out.println("Over Time!");
            }
        }
//        checkWhoScore();
        return checkWhoWon();
    }

    private Team aGoalIsScored() {
        int totalOverall = team1.getOverall() + team2.getOverall();


        int teamThatScored = (int) (Math.random() * 2);

        switch (teamThatScored) {
            case 0:
                if (calculateGoalProbability(team1, totalOverall)) {
                    return team1;
                }
            case 1:
                if (calculateGoalProbability(team2, totalOverall)) {
                    return team2;

                }

        }
        return null;


    }


    private boolean calculateGoalProbability(Team team, int totalOverall) {
        System.out.println(team.getName() + " has a great chance to score");

        int chanceToScore = (team.getOverall() * 100) / totalOverall;
        if (chanceToScore <= (int) (Math.random() * 100)) {
            System.out.println(team.getName() + " Score a gooooooooooooaaaaaal!!!");
            return true;
        }

        System.out.println(team.getName() + " wasted a great chance!!");
        return false;


    }

    public Team checkWhoWon() {

        if (team1Score > team2Score) {
            System.out.println(team1.getName() + " has won the game");
            winTeamList.add(team1);
            loseTeamList.add(team2);
            return team1;

        } else if (team1Score < team2Score) {
            System.out.println(team2.getName() + " has won the game");
            winTeamList.add(team2);
            loseTeamList.add(team1);
            return team2;

        } else {
            return null;
        }
    }

    public void playersDidGoal(Team team) {
        int randomPlayerGoal = (int) (Math.random() * (3 - 0 + 1) + 0);
        System.out.println("CONGRATULATION: " + team.choosePlayer(randomPlayerGoal).getName().toUpperCase());
    }


    public void penalties() {

        int penaltiesWinner = (int) (Math.random() * 3);
        if (penaltiesWinner == 1) {
            System.out.println(team1.getName() + " won the game after the penalties");
        } else {
            System.out.println(team2.getName() + " won the game after penalties");


        }


    }


}











