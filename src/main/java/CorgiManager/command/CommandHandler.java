package CorgiManager.command;
import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MissingArgumentException;

/**
 * This functional interface enables lambda expression to receive input in command instantiation
 * of COMMANDS HashMap variable in Parser class
 */
@FunctionalInterface
public interface CommandHandler {
    /**
     * Instantiates a Command object.
     *
     * @param input The array of user input strings representing the command and its arguments.
     * @return The instantiated Command object corresponding to the user's input.
     * @throws MissingArgumentException if a required command argument is missing.
     * @throws InvalidCommandException if the command provided is unrecognized.
     * @throws IncorrectFormatException if the command format does not match the expected structure.
     */
    Command handle(String[] input) throws MissingArgumentException, InvalidCommandException, IncorrectFormatException;
}
