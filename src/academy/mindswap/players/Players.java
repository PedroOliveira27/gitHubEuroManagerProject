package academy.mindswap.players;

public class Players {

    //PROPERTIES
    private String name;
    private int age;
    private Position positionType;
    private int overall;

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
}
