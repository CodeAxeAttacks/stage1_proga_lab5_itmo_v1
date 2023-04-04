import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <filename>");
            System.exit(1);
        }

        String filename = args[0];
        Parser parser = new Parser(filename);
        try {
            LabWorkCollection labWorkCollection = parser.parse();
            CommandProcessor processor = new CommandProcessor(labWorkCollection);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the LabWorkCollection program!\n" + "To check the list of commands type: help");
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                processor.executeCommand(input);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
