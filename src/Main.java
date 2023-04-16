import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
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
            CommandProcessor processor = new CommandProcessor(labWorkCollection, new InsertCommand(labWorkCollection, new BufferedReader(new InputStreamReader(System.in))),
                    new UpdateCommand(labWorkCollection,new BufferedReader(new InputStreamReader(System.in))), new ReplaceIfGreater(labWorkCollection, new BufferedReader(new InputStreamReader(System.in))),
                    new MinByMinimalPointCommand(labWorkCollection),new FilterGraterThanMinimalPoint(labWorkCollection, new BufferedReader(new InputStreamReader(System.in))),new ExecuteScriptCommand(labWorkCollection, new BufferedReader(new InputStreamReader(System.in))),
                    new ReplaceIfLowe(labWorkCollection, new BufferedReader(new InputStreamReader(System.in))));
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
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
