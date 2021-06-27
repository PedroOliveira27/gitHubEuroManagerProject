package academy.mindswap.players;

public class Player {

    //PROPERTIES
    private String name;
    private int age;
    private Position positionType;
    private int overall;

    public Player(String name, int age, Position positionType, int overall) {
        this.name = name;
        this.age = age;
        this.positionType = positionType;
        this.overall = overall;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Position getPositionType() {
        return positionType;
    }

    public int getOverall() {
        return overall;
    }

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
