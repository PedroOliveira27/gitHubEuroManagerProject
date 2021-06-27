package academy.mindswap.teams;

import academy.mindswap.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> team;

    /**
     * This constructor instantiate a Team object
     * with the name given as parameter
     *
     * @param name - a string that will be the name of the team
     */
    public Team(String name) {
        this.name = name;
        team = new ArrayList<>();
    }

	/**
	 * This method returns a String with all the
	 * players information in the current team
	 * @return String - all the players in the current team
	 */
    public String printPlayerList() {
        String playerList = "";
        for (Player p : team) {
            playerList += p.toString() + "\n";
        }
        return playerList;
    }

    /**
     * This method adds players to the respective team
     *
     * @param player
     */
    public void addPlayer(List<Player> player) {
        for (Player p : player) {
            team.add(p);
        }
    }

    /**
     * This method looks for a player, using his name, in the
     * player list(array list team), and returns the player,
     * if found.
     *
     * @param playerName
     * @return player if found the list of players
     * null if player name is invalid.
     */
    public Player choosePlayer(String playerName) {
        for (Player p : team) {
            if (p.getName().equals(playerName)) {
                return p;
            }
        }
        return null;
    }

    /**
     * This method looks for a player , using his index in the
	 * player list(array list team), and returns the player.
     *
     * @param playerIndex
     * @return the player of the chosen index
     */
    public Player choosePlayer(int playerIndex) {
        return team.get(playerIndex);
    }

    /**
     * This method gives the average overall of the teams players.
     *
     * @return the average overall of the teams players.
     */
    public int getOverall() {
        int overall = 0;
        for (Player p : team) {
            overall += p.getOverall();
        }
        return overall / team.size();
    }

    /**
     * @return team's name
     */
    public String getName() {
        return name;
    }

    /**
     * This method compares two teams based on their name.
     *
     * @param team - Team team to compare to the current team
     * @return true - if current team and team to be compared
	 * 					hold the same name
	 * 			false - if the name is different
     */
    public boolean isEqual(Team team) {
        if (this.getName().equals(team.getName())) {
            return true;
        }
        return false;
    }
}
