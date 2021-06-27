package academy.mindswap.game;

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

	/**
	 * This constructor creates a match between two teams
	 * @param team1 - team playing
	 * @param team2 - team playing
	 * @param out - PrintWriter which holds client socket output stream
	 */

	public Match(Team team1, Team team2, PrintWriter out) {
		this.team1 = team1;
		this.team2 = team2;
		this.out = out;
	}

	/**
	 * This method starts the match
	 * @return the winner of the match
	 */
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

	/**
	 * this method represents the full match
	 * @param maxMatchTime represents the  full match time that can be 90 minutes(regular time)
	 *                       or 30 minutes(extra time after the 90 minutes)
	 * @return the method CheckWhoWon(explained bellow),that checks the winning team
	 */
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

	/**
	 * This method gives a team the chance to  score a goal
	 * @return null when neither team scores or returns the team that score
	 */
	private Team aGoalIsScored() {
		int totalOverall = team1.getOverall() + team2.getOverall();


		int teamThatScored = RandomGenerator.generateRandom(0, 1);;

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

	/**
	 * this method calculates the probability of team having a chance to score based
	 * on their overall and the overall of the team that is playing against
	 * @param team receives the teams that are playing
	 * @param totalOverall the sum of the overall of both teams that are playing
	 * @return returns true when a team converts a chance into a goal and false when a team misses a chance
	 */
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

	/**
	 * This methods compares the number of goals of the teams playing
	 * @return the team that won the game or null if there is a tie
	 */
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


	/**
	 * This method prints the name of the player that score a goal.That player will be a Striker or a Midfielder
	 *  based on their index in the array list.The chosen index is calculated with a
	 *  random generator between integer 0 - 5
	 *
	 * @param team that scored a goal
	 */
	public void playerScore(Team team) {
		int randomPlayerGoal = RandomGenerator.generateRandom(0, 5);
		out.println(String.format(Messages.PLAYER_SCORED, team.choosePlayer(randomPlayerGoal).getName().toUpperCase()));
	}

	/**
	 * This method simulates a penalties shoot-out that will occur if the teams are tied after the 120 minutes mark
	 * the winner will be random decided with a random integer between 0-1
	 * @return the team that won the penalties and the game
	 */
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

	public String getMatch() {
		return team1.getName() + " is playing against " + team2.getName();
	}
}











