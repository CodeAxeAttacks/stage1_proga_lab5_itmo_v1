import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class InsertCommand implements Command {
    private final LabWorkCollection collection;

    public InsertCommand(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the LabWork data:");

        System.out.print("ID: ");
        String id = reader.readLine();

        System.out.print("Name: ");
        String name = reader.readLine();

        System.out.print("Coordinates (x y): ");
        String[] coordinatesInput = reader.readLine().split("\\s+");
        Coordinates coordinates = new Coordinates((float) Double.parseDouble(coordinatesInput[0]), Double.parseDouble(coordinatesInput[1]));


        System.out.print("Minimal Point: ");
        float minimalPoint = Float.parseFloat(reader.readLine());

        System.out.print("Difficulty (VERY_EASY, NORMAL, IMPOSSIBLE, INSANE, TERRIBLE): ");
        String difficultyInput = reader.readLine().toUpperCase();
        Difficulty difficulty = Difficulty.valueOf(difficultyInput);

        System.out.print("Discipline Name: ");
        String disciplineName = reader.readLine();

        System.out.print("Self Study Hours: ");
        int selfStudyHours = Integer.parseInt(reader.readLine());

        Discipline discipline = new Discipline(disciplineName, selfStudyHours);

        LabWork labWork = new LabWork();
        labWork.setId(Long.parseLong(id));
        labWork.setName(name);
        labWork.setCoordinates(coordinates);
        labWork.setMinimalPoint(minimalPoint);
        labWork.setDifficulty(difficulty);
        labWork.setDiscipline(discipline);

        collection.add(labWork);

        System.out.println("New LabWork added successfully.");
    }

    @Override
    public String getDescription() {
        return "insert";
    }
}
