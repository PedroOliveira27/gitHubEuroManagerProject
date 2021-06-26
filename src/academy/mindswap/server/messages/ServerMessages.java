package academy.mindswap.server.messages;

public class ServerMessages {

	public static final String WELCOME_SCREEN = "\n"+
					"---------------------------------\n" +
					"|    WELCOME TO EURO MANAGER    |\n" +
					"---------------------------------\n";
	public static final String COMMANDS_LIST = "\n" +
			"Available commands:\n" +
			"/help - list of commands\n" +
			"/play - start playing\n" +
			"/teams - list of teams\n" +
			"/lineup - players in your team\n";
	public static final String INVALID_COMMAND = "\nInvalid command.";
	public static final String CHOOSE_USERNAME = "Choose your username: ";
	public static final String WELCOME_USERNAME = "Welcome, ";
	public static final String UNAVAILABLE_USERNAME = " is not available. Choose another one.";
	public static final String CHOOSE_TEAM = "Choose your team: ";
}
