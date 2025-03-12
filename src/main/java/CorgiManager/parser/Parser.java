package CorgiManager.parser;

import CorgiManager.command.*;
import CorgiManager.command.CommandHandler;
import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MissingArgumentException;
import CorgiManager.task.Event;
import CorgiManager.task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private static final Map<String, CommandHandler> COMMANDS = new HashMap<>();


    // initialize commands
    static {
        COMMANDS.put("bye", input -> new ExitCommand());
        COMMANDS.put("list", input -> new ListCommand());
        COMMANDS.put("find", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'find' command");
            }
            return new FindCommand(input[1]);
        });
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

            int idxStart = input[1].indexOf("/from");
            int idxEnd = input[1].indexOf("/to");

            String eventName = input[1].substring(0, idxStart).trim();
            String eventStartTime = input[1].substring(idxStart + 1, idxEnd).trim();
            eventStartTime = eventStartTime.split(" ")[1];
            String eventEndTime = input[1].substring(idxEnd).split(" ", 2)[1];

            return new EventCommand(eventName, eventStartTime, eventEndTime);
        });
        COMMANDS.put("delete", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'delete' command");
            }
            int taskId = Integer.parseInt(input[1]) - 1;
            return new DeleteCommand(taskId);
        });
        COMMANDS.put("mark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'mark' command");
            }
            int taskId = Integer.parseInt(input[1]) - 1;
            return new MarkCommand(taskId);
        });
        COMMANDS.put("unmark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'unmark' command");
            }
            int taskId = Integer.parseInt(input[1]) - 1;
            return new UnmarkCommand(taskId);
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
