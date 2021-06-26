package academy.mindswap.teams;

import academy.mindswap.players.Players;
import academy.mindswap.players.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamHandler {

	public static Team createTeam(Team team, List<Players> players) {
		team.addPlayer(players);
		return team;
	}

	public static List<Players> portugal = Arrays.asList(
			new Players("Cristiano Ronaldo", 36, Position.STRIKER, 97),
			new Players("Bernardo Silva", 26, Position.STRIKER, 88),
			new Players("Joao Felix", 21, Position.STRIKER, 86),
			new Players("Joao Moutinho", 34, Position.MIDFIELDER, 88),
			new Players("Bruno Fernandes", 26, Position.MIDFIELDER, 86),
			new Players("Ruben Neves", 24, Position.MIDFIELDER, 81),
			new Players("Nelson Semedo", 27, Position.DEFENDER, 85),
			new Players("Joao Cancelo", 27, Position.DEFENDER, 84),
			new Players("Ruben Dias", 24, Position.DEFENDER, 80),
			new Players("Pepe", 38, Position.DEFENDER, 76),
			new Players("Rui Patricio", 33, Position.GOALKEEPER, 85)
	);

	public static List<Players> germany = Arrays.asList(
			new Players("Leroy Sané", 25, Position.STRIKER, 88),
			new Players("Serge Gnabry", 25, Position.STRIKER, 87),
			new Players("Timo Werner", 25, Position.STRIKER, 86),
			new Players("Toni Kroos", 31, Position.MIDFIELDER, 91),
			new Players("Joshua Kimmich", 26, Position.MIDFIELDER, 90),
			new Players("Kai Havertz", 22, Position.MIDFIELDER, 86),
			new Players("Niklas Sule",25, Position.DEFENDER, 83),
			new Players("Antonio Rudiger",28, Position.DEFENDER, 83),
			new Players("Matthias Ginter",27, Position.DEFENDER, 81),
			new Players("Jonathan Tah",25, Position.DEFENDER, 80),
			new Players("Manuel Neuer",35, Position.GOALKEEPER, 84)
	);

	public static List<Players> france = Arrays.asList(
			new Players("Kylian Mbappé", 22, Position.STRIKER, 91),
			new Players("Antoine Griezmann", 30, Position.STRIKER, 90),
			new Players("Oliver Giroud",34, Position.STRIKER, 83),
			new Players("N`Golo Kanté", 0, Position.MIDFIELDER, 90),
			new Players("Paul Pogba", 28, Position.MIDFIELDER, 89),
			new Players("Moussa Sissoko", 31, Position.MIDFIELDER, 86),
			new Players("Raphael Varane", 28, Position.DEFENDER, 88),
			new Players("Lucas Hernández", 25, Position.DEFENDER, 85),
			new Players("Clément Lenglet", 26, Position.DEFENDER, 83),
			new Players("Samuel Umtiti", 38, Position.DEFENDER, 82),
			new Players("Hugo Lloris", 33, Position.GOALKEEPER, 88)
	);

	public static List<Players> hungary = Arrays.asList(
			new Players("Rolland Sallai", 24, Position.STRIKER, 74),
			new Players("Nemanja Nikolics", 33, Position.STRIKER, 72),
			new Players("Kevin Varga", 25, Position.STRIKER, 70),
			new Players("David Siger", 30, Position.MIDFIELDER, 70),
			new Players("Adam Nagy", 26, Position.MIDFIELDER, 70),
			new Players("Laszl Kleinheisler", 27, Position.MIDFIELDER, 66),
			new Players("Willi Orban", 28, Position.DEFENDER, 78),
			new Players("Loic Nego", 30, Position.DEFENDER, 74),
			new Players("Attila Szalai", 23, Position.DEFENDER, 72),
			new Players("Gergo Lovrencsics", 32, Position.DEFENDER, 68),
			new Players("Peter Gulacsi", 31, Position.GOALKEEPER, 82)
	);
}
