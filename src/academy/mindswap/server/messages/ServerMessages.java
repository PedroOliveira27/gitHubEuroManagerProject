package academy.mindswap.server.messages;

public class ServerMessages {

	public static final String WELCOME_SCREEN = "\n"+
					"---------------------------------\n" +
					"|    WELCOME TO EURO MANAGER    |\n" +
					"---------------------------------\n";
	public static final String COMMANDS_LIST = "\n" +
			"Available commands:\n" +
			"/help - list of commands\n" +
			"/play - start playing a new game\n" +
			"/teams - list of teams\n" +
			"/lineup - players in your team\n" +
			"/train - train a player to increase his overall\n" +
			"/skip - skip a day\n" +
			"/timeline - days until your next match\n" +
			"/upcoming matches - all upcoming matches\n" +
			"/match - play your match when the day comes\n";
	public static final String INVALID_COMMAND = "\nInvalid command.";
	public static final String CHOOSE_USERNAME = "\nChoose your username: ";
	public static final String WELCOME_USERNAME = "\nWelcome, ";
	public static final String UNAVAILABLE_USERNAME = " is not available. Choose another one.";
	public static final String CHOOSE_TEAM = "\nChoose your team: ";
	public static final String AVAILABLE_TEAMS = "\nTeams list: \n";
	public static final String INVALID_TEAM = "\nInvalid team. Choose a valid team: ";
	public static final String LINEUP = " lineup.";
	public static final String INVALID_START = "\nYou must start playing, in order to be able " +
			"to see your team's lineup and manage it.\n";
	public static final String TRAINING_TARGET = "\nWho do you wish to train?\n" +
			"Provide his full name like it's written in the lineup.\n";
	public static final String INVALID_PLAYER = "\nInvalid player name.\n" +
			"Watch out for possible typos.\n" +
			"Also, you can check your lineup using command /lineup.\n";
	public static final String MATCH_DAY = "Your match is today.";
	public static final String DAYS_UNTIL_MATCH = " days until your match.\n" +
			"Use this time to train your players.";
	public static final String NOT_MATCH_DAY = "Your match is not today.";
	public static final String CLOSING_GAME = "Closing Euro Manager...";
}
