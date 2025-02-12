package CorgiManager.command;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MarkingOutOfBoundException;
import CorgiManager.exception.MissingArgumentException;

@FunctionalInterface
public interface CommandHandler {
    void handle(String[] input) throws MissingArgumentException, InvalidCommandException;
}
