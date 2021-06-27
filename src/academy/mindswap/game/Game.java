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
	 * This constructor creates a new group and sets the days until the first matches to three
	 * Start the match creation
     * @param out PrintWriter which holds client socket output stream
     * @param in  BufferedRead which holds client socket input stream
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


    /**
     * With this method the player chooses a team to play
     *
     * @param teamName a string with the name of the team
     * @return return the chosen team or null if the name is invalid
     */
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

    /**
     * This method gets random teams and pairs them two by two
     */
    private void createMatches() {
        for (int i = 0; i < matches.length; i++) {
            for (int j = 0; j < matches[i].length; j++) {
                Team team = randomTeam();
                assignedTeams.add(team);
                matches[i][j] = team;
            }
        }
    }

    /**
     * This method is used to generate a random match up from the available teams
     *
     * @return a random team
     */
    public Team randomTeam() {
        Team team = group.availableTeams.get(RandomGenerator.generateRandom(0, 3));
        while (assignedTeams.contains(team)) {
            team = group.availableTeams.get(RandomGenerator.generateRandom(0, 3));
        }
        return team;
    }

    /**
     * This method prints a list of the available teams
     *
     * @return String with the names of available teams
     */
    public String printTeams() {
        return group.printAvailableTeams();
    }

    /**
     * This method prints the eleven players of client team
     *
     * @return String with the names of the players
     */
    public String printLineUp() {
        return chosenTeam.printPlayerList();
    }

    /**
     * This methods prints the matches that will happen
     *
     * @return a string with the matches that will happen
     */
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

    /**
     * This simulates the passage of a day
     */
    public void passDay() {
        if (daysUntilMatch > 0) {
            daysUntilMatch--;
        }
    }

    /**
     * This method give us the number of days until the match
     *
     * @return the number of days until the match
     */
    public int getDaysUntilMatch() {
        return daysUntilMatch;
    }

    /**
	 * This method simulates the matches between all the available teams.
	 * If the client team looses, the second match between the two AI happens(the final).
	 * If the client team wins, returns that the client team is in the final.
     * @return,  true if the client team is in the final.False if not.
     */
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

	/**
	 * This method plays the final match.After the match is over,
	 * a choice is given to the client to start a new career.
	 * @return true if the client chooses yes,false if he chooses no.
	 */
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


	/**
	 * This method finds the match between the client team and the AI team.
	 * @return the match, or null if did not found the team.
	 */
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

	/**
	 * This method finds the match between the AI team's
	 * @return the match, or null if did not found the team.
	 */
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

	/**
	 *
	 * @return a string with name of the teams playing the final match.
	 */
	public String getFinalMatch() {
        return finalMatch.getMatch();
    }


}
