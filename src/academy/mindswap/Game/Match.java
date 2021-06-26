package academy.mindswap.Game;

import academy.mindswap.players.Position;
import academy.mindswap.teams.Team;
import academy.mindswap.util.Messages;
import academy.mindswap.util.RandomGenerator;


import java.util.LinkedList;
import java.util.List;

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
        System.out.printf(Messages.START_GAME,
                team1.getName(), team2.getName());
        team1Score = 0;
        team2Score = 0;
        double matchTime = 0;

        Team teamWinner = play(90);

        if (teamWinner == null) {
            teamWinner = play(30);
        }
        if (team1Score != team2Score) {
            System.out.printf(Messages.END_GAME, team1.getName(), team1Score, team2.getName(), team2Score);


        }
        return teamWinner;
    }

    private Team play(double maxMatchTime) {
        double currentmatchTime = 0;
        while (currentmatchTime < maxMatchTime) {
            Team team = aGoalIsScored();
            if (team == team1) {
                team1Score++;
                playerScore(team1);
                System.out.printf(Messages.CURRENT_SCORE, team1.getName(), team1Score, team2.getName(), team2Score);
            }
            if (team == team2) {
                team2Score++;
                playerScore(team2);
                System.out.printf(Messages.CURRENT_SCORE, team1.getName(), team1Score, team2.getName(), team2Score);
            }

            if (maxMatchTime / 2 == currentmatchTime) {
                System.out.println(Messages.HALF_TIME);
                System.out.printf(Messages.CURRENT_SCORE, team1.getName(), team1Score, team2.getName(), team2Score);
            }
            currentmatchTime += (maxMatchTime / 4);
        }
        if (team1Score == team2Score) {
            if (maxMatchTime == 30) {
                System.out.println(Messages.PENALTIES);
                penalties();


            } else if (maxMatchTime == 90) {
                System.out.printf(Messages.OVER_TIME);
            }
        }

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
        System.out.printf(Messages.GOAL_CHANCE, team.getName());

        int chanceToScore = (team.getOverall() * 100) / totalOverall;
        if (chanceToScore <= (int) (Math.random() * 100)) {
            System.out.printf(Messages.TEAM_SCORED, team.getName());
            return true;
        }

        System.out.printf(Messages.WASTED_CHANCE, team.getName());
        return false;


    }

    public Team checkWhoWon() {

        if (team1Score > team2Score) {
            System.out.printf(Messages.TEAM_WIN, team1.getName());
            winTeamList.add(team1);
            loseTeamList.add(team2);
            return team1;

        } else if (team1Score < team2Score) {
            System.out.printf(Messages.TEAM_WIN, team2.getName());
            winTeamList.add(team2);
            loseTeamList.add(team1);
            return team2;


        } else {
            return null;
        }
    }

    public void playerScore(Team team) {
        int randomPlayerGoal = RandomGenerator.generateRandom(0, 3);
        System.out.printf(Messages.PLAYER_SCORED, team.choosePlayer(randomPlayerGoal).getName().toUpperCase());
    }


    public void penalties() {

        int penaltiesWinner = RandomGenerator.generateRandom(0, 1);
        if (penaltiesWinner == 1) {
            System.out.printf(Messages.TEAM_WIN_PENALTIES, team1.getName());
            winTeamList.add(team1); // alterado sujeito a aprovaçao de Angelo

        } else {
            System.out.printf(Messages.TEAM_WIN_PENALTIES, team2.getName());
            winTeamList.add(team2); // alterado sujeito a aprovaçao de Angelo


        }


    }

    public List<Team> getWinTeamList() {
        return winTeamList;
    }

    public List<Team> getLoseTeamList() {
        return loseTeamList;
    }
}











