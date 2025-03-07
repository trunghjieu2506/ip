package CorgiManager.command;
import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MissingArgumentException;

@FunctionalInterface
public interface CommandHandler {
    Command handle(String[] input) throws MissingArgumentException, InvalidCommandException, IncorrectFormatException;
}
