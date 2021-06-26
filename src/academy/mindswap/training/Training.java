package academy.mindswap.training;

import academy.mindswap.players.Players;
import academy.mindswap.util.Messages;
import academy.mindswap.util.RandomGenerator;


public class Training {

    public static void developPlayers(Players player) {

        System.out.printf(Messages.PLAYER_TRAINING, player.getName());
        int playerDevelopment = 0;


        if (player.getAge() <= 21) {
            playerDevelopment = RandomGenerator.generateRandom(2, 6);
            player.setOverall(player.getOverall() + playerDevelopment);
            System.out.printf(Messages.IMPROVE_OVERALL, player.getName(), playerDevelopment, player.getOverall());
        } else if (player.getAge() > 21 && player.getAge() <= 25) {
            playerDevelopment = RandomGenerator.generateRandom(2, 4);
            ;
            player.setOverall(player.getOverall() + playerDevelopment);
            System.out.printf(Messages.IMPROVE_OVERALL, player.getName(), playerDevelopment, player.getOverall());

        } else {
            playerDevelopment = RandomGenerator.generateRandom(1, 3);
            player.setOverall(player.getOverall() + playerDevelopment);
            System.out.printf(Messages.IMPROVE_OVERALL, player.getName(), playerDevelopment, player.getOverall());


        }


    }


}
