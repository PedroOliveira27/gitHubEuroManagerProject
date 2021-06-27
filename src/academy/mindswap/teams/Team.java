package academy.mindswap.teams;

import academy.mindswap.players.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private String name;
	private List<Player> team;

	public Team(String name) {
		this.name = name;
		team = new ArrayList<>();
	}

	public String printPlayerList() {
		String playerList = "";
		for (Player p : team) {
			playerList += p.toString() + "\n";
		}
		return playerList;
	}

	public void addPlayer(List<Player> players) {
		for(Player p : players){
			team.add(p);
		}
	}

	public Player choosePlayer(String playerName) {
		for(Player p : team){
			if (p.getName().equals(playerName)){
				return p;
			}
		}
		return null;
	}

	public Player choosePlayer(int playerIndex) {
		return team.get(playerIndex);
	}


	public int getOverall() {
		int overall = 0;
		for (Player p : team) {
			overall += p.getOverall();
		}
		return overall / team.size();
	}

	public String getName() {
		return name;
	}

	public boolean isEqual(Team team) {
		if(this.getName().equals(team.getName())){
				return true;
		}
		return false;
	}
}
