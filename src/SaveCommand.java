import java.io.IOException;

public class SaveCommand implements Command {
    private final LabWorkCollection collection;

    public SaveCommand(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() {
        JsonWriter writer = new JsonWriter(collection, "result.json");
        try {
            writer.writeToJson();
            System.out.println("Collection saved to result.json.");
        } catch (IOException e) {
            System.out.println("Error saving collection to result.json: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "save";
    }
}
