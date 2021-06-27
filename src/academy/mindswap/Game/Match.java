package academy.mindswap.Game;

import academy.mindswap.teams.Team;
import academy.mindswap.util.Messages;
import academy.mindswap.util.RandomGenerator;

import java.io.PrintWriter;

public class Match {
	private final Team team1;
	private final Team team2;
	private int team1Score;
	private int team2Score;
	private final PrintWriter out;


	public Match(Team team1, Team team2, PrintWriter out) {
		this.team1 = team1;
		this.team2 = team2;
		this.out = out;
	}

	public Team start() {
		out.println(String.format(Messages.START_GAME,
				team1.getName(), team2.getName()));
		team1Score = 0;
		team2Score = 0;

		Team teamWinner = play(90);

		if (teamWinner == null) {
			teamWinner = play(30);
		}
		if (team1Score != team2Score) {
			out.println(String.format(Messages.END_GAME, team1.getName(), team1Score, team2.getName(), team2Score));
		}
		return teamWinner;
	}

	private Team play(double maxMatchTime) {
		double currentMatchTime = 0;
		while (currentMatchTime < maxMatchTime) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Team team = aGoalIsScored();
			if (team == team1) {
				team1Score++;
				playerScore(team1);
				out.println(String.format(Messages.CURRENT_SCORE, team1.getName(), team1Score, team2.getName(), team2Score));
			}
			if (team == team2) {
				team2Score++;
				playerScore(team2);
				out.println(String.format(Messages.CURRENT_SCORE, team1.getName(), team1Score, team2.getName(), team2Score));
			}

			if (maxMatchTime / 2 == currentMatchTime) {
				out.println(Messages.HALF_TIME);
				out.println(String.format(Messages.CURRENT_SCORE, team1.getName(), team1Score, team2.getName(), team2Score));
			}
			currentMatchTime += (maxMatchTime / 4);
		}
		if (team1Score == team2Score) {
			if (maxMatchTime == 30) {
				out.println(Messages.PENALTIES);
				return penalties();


			} else if (maxMatchTime == 90) {
				out.println(Messages.OVER_TIME);
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
		out.println(String.format(Messages.GOAL_CHANCE, team.getName()));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int chanceToScore = (team.getOverall() * 100) / totalOverall;
		if (chanceToScore <= (int) (Math.random() * 100)) {
			out.println(String.format(Messages.TEAM_SCORED, team.getName()));
			return true;
		}

		out.println(String.format(Messages.WASTED_CHANCE, team.getName()));
		return false;
	}

	public Team checkWhoWon() {
		if (team1Score > team2Score) {
			out.println(String.format(Messages.TEAM_WIN, team1.getName()));
			return team1;
		} else if (team1Score < team2Score) {
			out.println(String.format(Messages.TEAM_WIN, team2.getName()));
			return team2;
		} else {
			return null;
		}
	}

	public void playerScore(Team team) {
		int randomPlayerGoal = RandomGenerator.generateRandom(0, 3);
		out.println(String.format(Messages.PLAYER_SCORED, team.choosePlayer(randomPlayerGoal).getName().toUpperCase()));
	}

	public Team penalties() {

		int penaltiesWinner = RandomGenerator.generateRandom(0, 1);
		if (penaltiesWinner == 1) {
			out.println(String.format(Messages.TEAM_WIN_PENALTIES, team1.getName()));
			return team1;

		} else {
			out.println(String.format(Messages.TEAM_WIN_PENALTIES, team2.getName()));
			return team2;
		}
	}
}











