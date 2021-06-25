package academy.mindswap.Game;

import academy.mindswap.teams.Team;
import academy.mindswap.teams.TeamHandler;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Groups group;

    public Game() {
       group = new Groups();
    }
    public Game(Groups group) {
        this.group = group;
    }


    public void RandomGame (){
        int max=3;
        int min=0;
        int randomFirstTeamPlay = (int) (Math.random() * (max-min+1))+min;
        System.out.println(randomFirstTeamPlay);
        int randomSecontTeamPlay = (int) (Math.random() * (max-min+1))+min;
        System.out.println(randomSecontTeamPlay);
        if (randomFirstTeamPlay != randomSecontTeamPlay){
            System.out.println(group.createGroup.get(randomFirstTeamPlay).getName());
            System.out.println(group.createGroup.get(randomSecontTeamPlay).getName());
        }


    }

}
