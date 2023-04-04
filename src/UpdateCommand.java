import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdateCommand implements Command {
    private final LabWorkCollection collection;

    public UpdateCommand(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("ID: ");
        String idStr = reader.readLine();
        long id = Long.parseLong(idStr);
        LabWork labWork = collection.getById(id);
        if (labWork == null) {
            System.out.println("LabWork with ID " + id + " not found");
            return;
        }
        System.out.println("LabWork with ID " + id + ": " + labWork);
        System.out.print("Characteristic to update (name, coordinates, minimalPoint, difficulty, discipline): ");
        String characteristic = reader.readLine();
        switch (characteristic) {
            case "name":
                System.out.print("New name: ");
                String name = reader.readLine();
                labWork.setName(name);
                break;
            case "coordinates":
                System.out.print("Enter new X coordinate: ");
                String xStr = reader.readLine();
                System.out.print("Enter new Y coordinate: ");
                String yStr = reader.readLine();
                try {
                    double x = Double.parseDouble(xStr);
                    double y = Double.parseDouble(yStr);
                    Coordinates coordinates = new Coordinates((float) x, y);
                    labWork.setCoordinates(coordinates);
                    System.out.println("Coordinates of the lab work with ID " + id + " have been updated to (" + x + ", " + y + ").");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for coordinates.");
                }
                break;
            case "minimalPoint":
                System.out.print("New minimal point: ");
                String minimalPointStr = reader.readLine();
                float minimalPoint = Float.parseFloat(minimalPointStr);
                labWork.setMinimalPoint(minimalPoint);
                break;
            case "difficulty":
                System.out.print("New difficulty (VERY_EASY, NORMAL, IMPOSSIBLE, INSANE, TERRIBLE): ");
                String difficultyStr = reader.readLine();
                Difficulty difficulty = Difficulty.valueOf(difficultyStr);
                labWork.setDifficulty(difficulty);
                break;
            case "discipline":
                System.out.print("New discipline (name, selfStudyHours): ");
                String discipline = reader.readLine();
                switch (discipline) {
                    case "name":
                        System.out.print("New name: ");
                        String name1 = reader.readLine();
                        labWork.getDiscipline().setName(name1);
                        break;
                    case "selfStudyHours":
                        System.out.print("New self-study hours: ");
                        String selfStudyHoursStr = reader.readLine();
                        int selfStudyHours = Integer.parseInt(selfStudyHoursStr);
                        labWork.getDiscipline().setSelfStudyHours(selfStudyHours);
                        break;
                    default:
                        System.out.println("Unknown discipline characteristic: " + discipline);
                        break;
                }
                break;
            default:
                System.out.println("Unknown characteristic: " + characteristic);
                break;
        }
        System.out.println("LabWork with ID " + id + " updated: " + labWork);
    }

    @Override
    public String getDescription() {
        return "update";
    }
}
