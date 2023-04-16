import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.lang.reflect.Method;

public class ExecuteScriptCommand implements Command {
    private final LabWorkCollection collection;
    public  BufferedReader rd;
    static Stack<String> stackWithFiles = new Stack<>();
    static Stack<BufferedReader> stackWithReaders = new Stack<>();

    public ExecuteScriptCommand(LabWorkCollection collection, BufferedReader bufferedReader) {
        this.collection = collection;
        this.rd = bufferedReader;
    }



    @Override
    public void execute() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        System.out.print("fileName: ");
        String fileName = rd.readLine();
        stackWithFiles.push(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            File file = new File(fileName);
            while (!file.exists()) {
                System.out.println("File not found. Please enter a valid file name:");
                fileName = rd.readLine();
                file = new File(fileName);
            }


            stackWithReaders.push(reader);
            collection.changeReaders(reader);
            String input;
            while ((input = reader.readLine()) != null) {

                if (input.charAt(0) == '/') {
                    System.out.println(input.substring(1));
                } else {
                    Map<String, Command> processor = collection.sendCommandMap();
                    String[] parts = input.split("\\s+");
                    String commandName = parts[0];
                    Command command = processor.get(commandName);
                    command.execute();
                }
            }

        }catch (Exception e){
            System.out.println("ggg");
        }finally {
            stackWithReaders.pop();
            try {
                collection.changeReaders(stackWithReaders.peek());
            }catch (EmptyStackException e){
                collection.changeReaders(new BufferedReader(new InputStreamReader(System.in)));
            }
        }
    }


    @Override
    public String getDescription() {
        return "execute_script";
    }
    public void changeReader(BufferedReader bufferedReader){
        this.rd = bufferedReader;
    }
}
