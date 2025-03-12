package CorgiManager.parser;

import CorgiManager.command.*;
import CorgiManager.command.CommandHandler;
import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MissingArgumentException;
import CorgiManager.ui.Ui;

import java.util.HashMap;
import java.util.Map;

/**
 * Parser Class parses user input strings and translates them into executable command objects.
 * The Parser class maintains a mapping between command keywords and their corresponding command handlers.
 * It ensures correct command syntax by throwing appropriate exceptions
 * if the command format is incorrect or incomplete.
 */
public class Parser {
    private static final Map<String, CommandHandler> COMMANDS = new HashMap<>();

    /**
     * Maps command keywords to their respective command handlers for command instantiation.
     */
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
                throw new IncorrectFormatException("Incorrect command syntax\n" + Ui.indent +  "-- deadline <task_name> /by <date>: create a task with a deadline");
            }

            int idx = input[1].indexOf("/by");
            String deadlineName = input[1].substring(0, idx).trim();
            String deadlineTime = input[1].substring(idx + 1).trim().split(" ", 2)[1];

            return new DeadlineCommand(deadlineName, deadlineTime);
        });
        COMMANDS.put("event", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'event' command");
            }
            if (!input[1].contains(" /from ") || !input[1].contains(" /to ")) {
                throw new IncorrectFormatException("Incorrect command syntax\n" + Ui.indent + "-- event <event_name> /from <start_date_time> /to <end_date_time>: create an event");
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

    /**
     * Parses the user's input command string into a concrete Command object that can be executed.
     *
     * @param input Raw command input provided by the user.
     * @return A Command object corresponding to the parsed user input.
     * @throws InvalidCommandException if the command keyword is unrecognized.
     * @throws MissingArgumentException if required arguments are missing from the command.
     * @throws IncorrectFormatException if the command does not follow the required syntax.
     */
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
