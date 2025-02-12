@FunctionalInterface
public interface CommandHandler {
    void handle(String[] input) throws MissingArgumentException;
}
