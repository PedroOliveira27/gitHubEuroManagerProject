package academy.mindswap.teams;

import academy.mindswap.players.Player;
import academy.mindswap.players.Position;

import java.util.Arrays;
import java.util.List;

public class TeamHandler {
	/**
	 * 
	 * @param team
	 * @param players
	 * @return
	 */

	public static Team createTeam(Team team, List<Player> players) {
		team.addPlayer(players);
		return team;
	}

	public static List<Player> portugal = Arrays.asList(
			new Player("Cristiano Ronaldo", 36, Position.STRIKER, 97),
			new Player("Bernardo Silva", 26, Position.STRIKER, 88),
			new Player("Joao Felix", 21, Position.STRIKER, 86),
			new Player("Joao Moutinho", 34, Position.MIDFIELDER, 88),
			new Player("Bruno Fernandes", 26, Position.MIDFIELDER, 86),
			new Player("Ruben Neves", 24, Position.MIDFIELDER, 81),
			new Player("Nelson Semedo", 27, Position.DEFENDER, 85),
			new Player("Joao Cancelo", 27, Position.DEFENDER, 84),
			new Player("Ruben Dias", 24, Position.DEFENDER, 80),
			new Player("Pepe", 38, Position.DEFENDER, 76),
			new Player("Rui Patricio", 33, Position.GOALKEEPER, 85)
	);

	public static List<Player> germany = Arrays.asList(
			new Player("Leroy Sané", 25, Position.STRIKER, 88),
			new Player("Serge Gnabry", 25, Position.STRIKER, 87),
			new Player("Timo Werner", 25, Position.STRIKER, 86),
			new Player("Toni Kroos", 31, Position.MIDFIELDER, 91),
			new Player("Joshua Kimmich", 26, Position.MIDFIELDER, 90),
			new Player("Kai Havertz", 22, Position.MIDFIELDER, 86),
			new Player("Niklas Sule",25, Position.DEFENDER, 83),
			new Player("Antonio Rudiger",28, Position.DEFENDER, 83),
			new Player("Matthias Ginter",27, Position.DEFENDER, 81),
			new Player("Jonathan Tah",25, Position.DEFENDER, 80),
			new Player("Manuel Neuer",35, Position.GOALKEEPER, 84)
	);

	public static List<Player> france = Arrays.asList(
			new Player("Kylian Mbappé", 22, Position.STRIKER, 91),
			new Player("Antoine Griezmann", 30, Position.STRIKER, 90),
			new Player("Oliver Giroud",34, Position.STRIKER, 83),
			new Player("N`Golo Kanté", 0, Position.MIDFIELDER, 90),
			new Player("Paul Pogba", 28, Position.MIDFIELDER, 89),
			new Player("Moussa Sissoko", 31, Position.MIDFIELDER, 86),
			new Player("Raphael Varane", 28, Position.DEFENDER, 88),
			new Player("Lucas Hernández", 25, Position.DEFENDER, 85),
			new Player("Clément Lenglet", 26, Position.DEFENDER, 83),
			new Player("Samuel Umtiti", 38, Position.DEFENDER, 82),
			new Player("Hugo Lloris", 33, Position.GOALKEEPER, 88)
	);

	public static List<Player> hungary = Arrays.asList(
			new Player("Rolland Sallai", 24, Position.STRIKER, 74),
			new Player("Nemanja Nikolics", 33, Position.STRIKER, 72),
			new Player("Kevin Varga", 25, Position.STRIKER, 70),
			new Player("David Siger", 30, Position.MIDFIELDER, 70),
			new Player("Adam Nagy", 26, Position.MIDFIELDER, 70),
			new Player("Laszl Kleinheisler", 27, Position.MIDFIELDER, 66),
			new Player("Willi Orban", 28, Position.DEFENDER, 78),
			new Player("Loic Nego", 30, Position.DEFENDER, 74),
			new Player("Attila Szalai", 23, Position.DEFENDER, 72),
			new Player("Gergo Lovrencsics", 32, Position.DEFENDER, 68),
			new Player("Peter Gulacsi", 31, Position.GOALKEEPER, 82)
	);
}
