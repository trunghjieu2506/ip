package corgimanager.command;
import corgimanager.exception.InvalidCommandException;
import corgimanager.exception.MissingArgumentException;

@FunctionalInterface
public interface CommandHandler {
    void handle(String[] input) throws MissingArgumentException, InvalidCommandException;
}
