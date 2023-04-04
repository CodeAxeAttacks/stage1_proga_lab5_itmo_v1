import java.io.*;

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

        File file = new File(fileName);
        while (!file.exists()) {
            System.out.println("File not found. Please enter a valid file name:");
            fileName = rd.readLine();
            file = new File(fileName);
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String input;
        while ((input = reader.readLine()) != null) {
            CommandProcessor processor = new CommandProcessor(collection);
            if (input.charAt(0) == '/') {
                System.out.println(input.substring(1));
            }
            else {
                processor.executeCommand(input);
            }
        }
        reader.close();
    }


    @Override
    public String getDescription() {
        return "execute_script";
    }
}
