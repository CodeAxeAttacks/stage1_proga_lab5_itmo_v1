import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private final LabWorkCollection collection;
    private final Map<String, Command> commands;
    private final CommandHistory history;

    public CommandProcessor(LabWorkCollection collection) {
        this.collection = collection;
        commands = new HashMap<>();
        history = new CommandHistory();
        commands.put("show", new ShowCommand(collection));
        commands.put("info", new InfoCommand(collection));
        commands.put("help", new HelpCommand(collection));
        commands.put("clear", new ClearCommand(collection));
        commands.put("min_by_minimal_point", new MinByMinimalPointCommand(collection));
        commands.put("save", new SaveCommand(collection));
        commands.put("group_counting_by_name", new GroupCountingByNameCommand(collection));
        commands.put("history", new HistoryCommand(history));
        commands.put("remove", new RemoveCommand(collection));
        commands.put("insert", new InsertCommand(collection));
        commands.put("update", new UpdateCommand(collection));
        commands.put("replace_if_greater", new ReplaceIfGreater(collection));
        commands.put("filter_greater_than_minimal_point", new FilterGraterThanMinimalPoint(collection));
        commands.put("execute_script", new ExecuteScriptCommand(collection));
        commands.put("replace_if_lowe", new ReplaceIfLowe(collection));
    }

    public void executeCommand(String input) throws IOException {
        String[] parts = input.split("\\s+");
        String commandName = parts[0];

        Command command = commands.get(commandName);
        if (command == null) {
            System.out.println("Unknown command: " + commandName);
            return;
        }

        command.execute();
        history.addCommand(command);
    }
}