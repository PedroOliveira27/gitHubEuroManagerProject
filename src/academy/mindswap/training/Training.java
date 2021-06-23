package academy.mindswap.training;

import academy.mindswap.players.Players;


public class Training {

    public static void developPlayers(Players player) {

        System.out.println("Player x, picked " + player.getName() + " for a training session");
        int playerDevelopment = 0;


        if (player.getAge() <= 21) {
            playerDevelopment = (int) (Math.random() * 5);
            System.out.println("playerDevelopment = " + playerDevelopment);
            player.setOverall(player.getOverall() + playerDevelopment);
            System.out.println(player.getName() + " improve is overall to " + player.getOverall());
        } else if (player.getAge() > 21 && player.getAge() <= 25) {
            playerDevelopment = (int) (Math.random() * 3);
            player.setOverall(player.getOverall() + playerDevelopment);
            System.out.println(player.getName() + " improve is overall to " + player.getOverall());

        } else {
            playerDevelopment = (int) (Math.random() * 1);
            player.setOverall(player.getOverall() + playerDevelopment);
            System.out.println(player.getName() + " improve is overall to " + player.getOverall());


        }


    }


}
