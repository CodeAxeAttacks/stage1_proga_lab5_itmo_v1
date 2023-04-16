import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaveCommand implements Command {
    private final LabWorkCollection collection;

    public SaveCommand(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() throws IOException {
        System.out.println("If you want to save changes to a new file reply: 1" + "\n"
                + "Else, if you want to change the current file reply: 0");
        JsonWriter writerT = new JsonWriter(collection, "testing.json");
        JsonWriter writerR = new JsonWriter(collection, "result.json");
            try{
                writerT.writeToJson();
                System.out.println("Collection saved to testing.json.");
            } catch (IOException e) {
                System.out.println("Error saving collection to testing.json: " + e.getMessage());
            }


    }

    @Override
    public String getDescription() {
        return "save";
    }
}
