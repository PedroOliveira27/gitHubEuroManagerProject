package academy.mindswap.training;

import academy.mindswap.players.Player;
import academy.mindswap.util.Messages;
import academy.mindswap.util.RandomGenerator;


public class Training {

    /**
     * This method improves the overall of chosen player.Younger players can
     * improve their overall far more than older players
     * @param player to train
     * @return returns the overall of the play after improvement
     */
    public static String developPlayers(Player player) {

//        System.out.printf(Messages.PLAYER_TRAINING, player.getName());
        int playerDevelopment = 0;


        if (player.getAge() <= 21) {
            playerDevelopment = RandomGenerator.generateRandom(2, 6);
            player.setOverall(player.getOverall() + playerDevelopment);

            return String.format(Messages.IMPROVE_OVERALL, player.getName(), playerDevelopment, player.getOverall());

        } else if (player.getAge() > 21 && player.getAge() <= 25) {
            playerDevelopment = RandomGenerator.generateRandom(2, 4);
            player.setOverall(player.getOverall() + playerDevelopment);

            return String.format(Messages.IMPROVE_OVERALL, player.getName(), playerDevelopment, player.getOverall());

        } else {
            playerDevelopment = RandomGenerator.generateRandom(1, 3);
            player.setOverall(player.getOverall() + playerDevelopment);

            return String.format(Messages.IMPROVE_OVERALL, player.getName(), playerDevelopment, player.getOverall());
        }
    }
}
