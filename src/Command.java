import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface Command {
    void execute() throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;
    String getDescription();
}
