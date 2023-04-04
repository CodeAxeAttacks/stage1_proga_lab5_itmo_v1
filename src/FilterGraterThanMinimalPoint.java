import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class FilterGraterThanMinimalPoint implements Command {
    private final LabWorkCollection collection;

    public FilterGraterThanMinimalPoint(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("minimalPoint: ");
        float minimalPoint = Float.parseFloat(reader.readLine());
        Collection<LabWork> labWorks = collection.getAll();
        for (LabWork labWork : labWorks) {
            if (labWork.getMinimalPoint() > minimalPoint) {
                System.out.println("ID: " + labWork.getId());
                System.out.println("Name: " + labWork.getName());
                System.out.println("Coordinates: (" + labWork.getCoordinates().getX() + ", " + labWork.getCoordinates().getY() + ")");
                System.out.println("Creation Date: " + labWork.getCreationDate());
                System.out.println("Minimal Point: " + labWork.getMinimalPoint());
                System.out.println("Difficulty: " + labWork.getDifficulty());
                System.out.println("Discipline: " + labWork.getDiscipline().getName() + " (self-study hours: " + labWork.getDiscipline().getSelfStudyHours() + ")");
                System.out.println("------------------------");
            }
        }
    }

    @Override
    public String getDescription() {
        return "filter_greater_than_minimal_point";
    }
}
