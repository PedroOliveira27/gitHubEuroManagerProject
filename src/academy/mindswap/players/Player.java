package academy.mindswap.players;

public class Player {

    //PROPERTIES
    private String name;
    private int age;
    private Position positionType;
    private int overall;

    /**
     * This contructor creates a player that will later be added to their respective teams.
     *
     * @param name         a String with the name of player
     * @param age          a int that represents the age of player
     * @param positionType the position of the player
     * @param overall      a int that represents the overall(ability) of the player
     */
    public Player(String name, int age, Position positionType, int overall) {
        this.name = name;
        this.age = age;
        this.positionType = positionType;
        this.overall = overall;
    }

    /**
     * @return a string with the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * @return a int with the age of the player
     */
    public int getAge() {
        return age;
    }

    /**
     * @return the respective position of the player
     * stored in the enum Position
     */
    public Position getPositionType() {
        return positionType;
    }

    /**
     *
     * @return the overall of the player
     */
    public int getOverall() {
        return overall;
    }

    /**
     * set a new value to the overall of the player after the training session(Method Training in the class Training)
     * @param overall
     */
    public void setOverall(int overall) {
        this.overall = overall;
    }

    @Override
    public String toString() {
        return "| " + name + " | " +
                overall + " | " +
                positionType + " | " +
                age;
    }
}
