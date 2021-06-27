package academy.mindswap.game;

import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;
import academy.mindswap.util.Messages;
import academy.mindswap.util.RandomGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Game {
	private final Group group;
	private Team chosenTeam;
	private int daysUntilMatch;
	private ArrayList<Team> assignedTeams;
	private Team[][] matches;
	private PrintWriter out;
	private BufferedReader in;
	private Match finalMatch;
	private Team euroWinner;

	/**
	 *
	 * @param out
	 * @param in
	 */
	public Game(PrintWriter out, BufferedReader in) {
		group = new Group();
		daysUntilMatch = 3;
		matches = new Team[2][2];
		assignedTeams = new ArrayList<>();
		this.out = out;
		this.in = in;
		createMatches();
	}


//	public void play() {
////		if (team1 != team2 && team1 != team3 && team1 != team4 && team2 != team3 && team2 != team4 && team3 != team4) {  // alterado sujeito a aprovaçao de Angelo
////			Match match = new Match(team1, team2);
////			Match match1 = new Match(team3, team4);
////			match.start();
////			match1.start();
////			Match match2 = new Match(match.getWinTeamList().get(0), match1.getWinTeamList().get(0));
////			System.out.printf(Messages.START_FINAL);
////			match2.start();
////			System.out.printf(Messages.END_FINAL, match2.getWinTeamList().get(0).getName());
////			return;
////
////		}
//		play(); //alterado sujeito a aprovaçao de Angelo
//
//	}

	public Team chooseTeam(String teamName) {
		Team team;

		switch (teamName.toLowerCase()) {
			case "portugal":
				team = TeamHandler.createTeam(new Team("Portugal"), TeamHandler.portugal);
				break;
			case "hungary":
				team = TeamHandler.createTeam(new Team("Hungary"), TeamHandler.hungary);
				break;
			case "germany":
				team = TeamHandler.createTeam(new Team("Germany"), TeamHandler.germany);
				break;
			case "france":
				team = TeamHandler.createTeam(new Team("France"), TeamHandler.france);
				break;
			default:
				team = null;
				break;
		}

		chosenTeam = team;
		return team;
	}

	private void createMatches() {
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				Team team = randomTeam();
				assignedTeams.add(team);
				matches[i][j] = team;
			}
		}
	}

	public Team randomTeam() {
		Team team = group.availableTeams.get(RandomGenerator.generateRandom(0, 3));
		while (assignedTeams.contains(team)) {
			team = group.availableTeams.get(RandomGenerator.generateRandom(0, 3));
		}
		return team;
	}

	public String printTeams() {
		return group.printAvailableTeams();
	}

	public String printLineUp() {
		return chosenTeam.printPlayerList();
	}

	public String printMatches() {
		String matches = "";
		for (int i = 0; i < this.matches.length; i++) {
			matches += "\nMatch #" + (i + 1) + "\n";
			for (int j = 0; j < this.matches[i].length; j++) {
				matches += this.matches[i][j].getName();
				if (j < this.matches[i].length - 1) {
					matches += " - ";
				}
			}
		}
		return matches;
	}

	public void passDay() {
		if (daysUntilMatch > 0) {
			daysUntilMatch--;
		}
	}

	public int getDaysUntilMatch() {
		return daysUntilMatch;
	}

	public boolean play() {
		Match match = findPlayerMatch();
		Team winnerPlayer = match.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.println("Second match of the day will happen now");
		match = findAIMatch();
		Team winnerAI = match.start();
		finalMatch = new Match(winnerPlayer, winnerAI, out);
		out.println("\n" + winnerPlayer.getName() + " and " + winnerAI.getName() + " are in the finals!\n");
		if (!(winnerPlayer.isEqual(chosenTeam))) {
			out.println(Messages.GAME_OVER);
			try {
				String choice = in.readLine().toLowerCase();
				switch (choice) {
					case "y":
					case "yes":
						return false;
					case "n":
					case "no":
					default:
						playFinal();
						return false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		daysUntilMatch = 3;
		return true;
	}

	public boolean playFinal() {
		out.println(Messages.START_FINAL);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Team euroWinner = finalMatch.start();
		out.println(String.format(Messages.END_FINAL, euroWinner.getName()));
		out.println(Messages.GAME_OVER);
		String choice = null;
		try {
			choice = in.readLine().toLowerCase();
			switch (choice) {
				case "y":
				case "yes":
					return true;
				case "n":
				case "no":
				default:
					return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private Match findPlayerMatch() {
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				if (matches[i][j].isEqual(chosenTeam)) {
					if (j == 1) {
						return new Match(chosenTeam, matches[i][0], out);
					} else if (j == 0) {
						return new Match(chosenTeam, matches[i][1], out);
					}
				}
			}
		}
		return null;
	}

	private Match findAIMatch() {
		for (int i = 0; i < matches.length; i++) {
			for (int j = 0; j < matches[i].length; j++) {
				if (matches[i][j].isEqual(chosenTeam)) {
					if (i == 1) {
						return new Match(matches[0][0], matches[0][1], out);
					} else if (i == 0) {
						return new Match(matches[1][0], matches[1][1], out);
					}
				}
			}
		}
		return null;
	}


//    public void knockoutStages(Match match2) {
//      match2 = new Match(match2.getWinTeamList().get(0), match2.getWinTeamList().get(1));
//
//        match2.start();
//
//
//    }


}
