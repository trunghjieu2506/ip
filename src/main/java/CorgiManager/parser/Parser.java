package CorgiManager.parser;

import CorgiManager.command.*;
import CorgiManager.command.CommandHandler;
import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MissingArgumentException;
import CorgiManager.task.Event;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final Map<String, CommandHandler> COMMANDS = new HashMap<>();


    // initialize commands
    static {
        COMMANDS.put("bye", input -> new ExitCommand());
        COMMANDS.put("list", input -> new ListCommand());
        COMMANDS.put("todo", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'todo' command");
            }
            return new ToDoCommand(input[1]);
        });
        COMMANDS.put("deadline", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'deadline' command");
            }
            if (!input[1].contains(" /by ")) {
                throw new IncorrectFormatException("Incorrect command syntax");
            }
            return new DeadlineCommand(input[1]);
        });
        COMMANDS.put("event", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'event' command");
            }
            if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
                throw new IncorrectFormatException("Incorrect command syntax");
            }
            return new EventCommand(input[1]);
        });
        COMMANDS.put("delete", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'delete' command");
            }
            return new DeleteCommand(input[1]);
        });
        COMMANDS.put("mark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'mark' command");
            }
            return new MarkCommand(input[1]);
        });
        COMMANDS.put("unmark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'unmark' command");
            }
            return new UnmarkCommand(input[1]);
        });
    }
    public static Command parseCommand(String input) throws InvalidCommandException, MissingArgumentException, IncorrectFormatException {
        String[] command = input.split(" ", 2);
        if (COMMANDS.containsKey(command[0])) {
            //handle method is defined in CommandHandler
            return COMMANDS.get(command[0]).handle(command);
        } else {
            throw new InvalidCommandException("Command does not exist!!");
        }
    }
}
