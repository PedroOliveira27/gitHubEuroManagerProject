package academy.mindswap.Game;

import academy.mindswap.teams.Team;

public class Match {
    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;


    public Match(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    public Team play() {
        System.out.println("Match day!!!Welcome to the stadium!Today\n " +
                team1.getName() + " is playing against " + team2.getName());
         team1Score = 0;
         team2Score = 0;
        double matchTime = 0;

        while (matchTime < 90) {
            Team team = aGoalIsScored();
            if (team == team1)
                team1Score++;
            if (team == team2)
                team2Score++;
            System.out.println("[" + team1.getName() + " " + team1Score + "]" + " - " +
                    "[" + team2.getName() + " " + team2Score + "]");
            matchTime += 22.5;

            if (matchTime == 45) {
                System.out.println("It`s half-time.Its time to give the players a motivational speech!.Lets start the second half");
            }

            if (team1Score > team2Score) {
                System.out.println(team1.getName() + " has won the game");

            }


        }
        Team winner = checkWhoWon();


        System.out.println("The game has ended!!The final score is \n" +
                "[" + team1.getName() + " " + team1Score + "]" + " - " +
                "[" + team2.getName() + " " + team2Score + "]");

//        System.out.println("The starting eleven for " + team1.getName() + " is ");
        return winner;
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
        System.out.println(chanceToScore);
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

            return team1;

        } else if (team1Score < team2Score) {
            System.out.println(team2.getName() + " has won the game");

            return team2;

        } else {
            System.out.println("At the 90 minutes mark the games is tied");
            return null;
        }


    }
}








