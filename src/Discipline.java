public class Discipline {
    private String name;
    private int selfStudyHours;

    public String getName() {
        return name;
    }

    public Discipline(String name, int selfStudyHours) {
        this.name = name;
        this.selfStudyHours = selfStudyHours;
    }

    public int getSelfStudyHours() {
        return selfStudyHours;
    }
}