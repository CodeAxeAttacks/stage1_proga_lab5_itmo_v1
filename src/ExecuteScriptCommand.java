import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteScriptCommand implements Command {
    private final LabWorkCollection collection;

    public ExecuteScriptCommand(LabWorkCollection collection) {
        this.collection = collection;
    }

    @Override
    public void execute() throws IOException {
        BufferedReader rd = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("fileName: ");
        String fileName = rd.readLine();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String input;
        while ((input = reader.readLine()) != null) {
            CommandProcessor processor = new CommandProcessor(collection);
            processor.executeCommand(input);
        }
        reader.close();
    }

    @Override
    public String getDescription() {
        return "execute_script";
    }
}
