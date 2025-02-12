import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class CorgiManager {
    public static final String partition = "************************************************************************";
    public static final String indent = "     ";

    public static final Map<String, CommandHandler> COMMANDS = new HashMap<>();
    // initialize commands
    static {
        COMMANDS.put("bye", input -> corgiPrint("Bye. Hope to see you again soon!"));
        COMMANDS.put("list", input -> listTask());
        COMMANDS.put("todo", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'todo' command");
            }
            addTask(input);});
        COMMANDS.put("deadline", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'deadline' command");
            }
            addTask(input);});
        COMMANDS.put("event", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'event' command");
            }
            addTask(input);});
        COMMANDS.put("mark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'mark' command");
            }
            addTask(input);});
        COMMANDS.put("unmark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'unmark' command");
            }
            addTask(input);});
    }
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello! I'm Corgi");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            String[] command = input.split(" ", 2);
            try {
                if (COMMANDS.containsKey(command[0])) {
                    COMMANDS.get(command[0]).handle(command);
                    if (command[0].equalsIgnoreCase("bye")) {
                        break;
                    }
                } else {
                    throw new InvalidCommandException("Command does not exist!!");
                }
            } catch (MissingArgumentException e) {
                corgiPrint(e.getMessage());
            } catch (InvalidCommandException e) {
                corgiPrint(e.getMessage());
                corgiGuide();
            }
        }


//            if (input.equalsIgnoreCase("bye")) {
//                corgiPrint("Bye. Hope to see you again soon!");
//                break;
//            }
//            else if (input.equalsIgnoreCase("list")) {
//                listTask();
//            }
//            else if (input.startsWith("mark")) {
//                markTask(parts[1]);
//            }
//            else if (input.startsWith("unmark")) {
//                unmarkTask(parts[1]);
//            }
//            else if (input.startsWith("todo")) {
//                addTask(parts[1]);
//            }
//            else if (input.startsWith("deadline")) {
//                addDeadline(parts[1]);
//            }
//            else if (input.startsWith("event")) {
//                addEvent(parts[1]);
//            }
//            else {
//                corgiGuide();
//            }
//        }
    }

    public static void corgiPrint(String input) {
        System.out.println(indent + partition);
        System.out.println(indent + input);
        System.out.println(indent + partition);
    }

    private static void corgiGuide() {
        corgiPrint("""
            How to Corgi:
            -- todo <task_name>: add a task to the list
            -- list: show list of tasks
            -- mark <task_number>: mark a task as done
            -- unmark <task_number>: unmark a task
            -- deadline <task_name> /by <date>: create a task with a deadline
            -- event <event_name> /from <start_date_time> /to <end_date_time>: create an event
        """);
    }

    public static void listTask() {
        System.out.println(indent + partition);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(indent + (i + 1) + "." + tasks.get(i).getStatusIcon());
        }
        System.out.println(indent + partition);
    }

    public static void addTask(String[] input){
        ToDo task = new ToDo(input[1]);
        tasks.add(task);

        corgiPrint(String.format("Added:\n%s%s\n%sYou now have %d tasks in the list.",
                indent, task.getStatusIcon(), indent, tasks.size()));
    }

    public static void addDeadline(String input) {
        int idx = input.indexOf("/by");

        String deadlineName = input.substring(0, idx).trim();
        // split the input into two parts (by and ...)
        String deadlineTime = input.substring(idx + 1).trim().split(" ", 2)[1];

        Deadline deadline = new Deadline(deadlineName, deadlineTime);
        tasks.add(deadline);

        corgiPrint(String.format("Added:\n%s%s\n%sYou now have %d tasks in the list.",
        indent, deadline.getStatusIcon(), indent, tasks.size()));
    }

    public static void addEvent(String input) {
        int idxStart = input.indexOf("/from");
        int idxEnd = input.indexOf("/to");

        String eventName = input.substring(0, idxStart).trim();
        String eventStartTime = input.substring(idxStart + 1, idxEnd).trim();
        eventStartTime = eventStartTime.split(" ")[1];
        String eventEndTime = input.substring(idxEnd).split(" ", 2)[1];

        Event event = new Event(eventName, eventStartTime, eventEndTime);
        tasks.add(event);

        corgiPrint(String.format("Added:\n%s%s\n%sYou now have %d tasks in the list.",
        indent, event.getStatusIcon(), indent, tasks.size()));
    }

    public static void markTask(String input) {
        if (!(input.split(" ").length == 1)) {
            // if the command line is incorrect, provide guide
            corgiGuide();
        }
        else {
            int taskIndex = Integer.parseInt(input) - 1;
            if (taskIndex >= 0 && taskIndex <= tasks.size()) {
                tasks.get(taskIndex).isDone = true;
                corgiPrint("Nice. I 've marked this task as done:\n"
                        + indent + tasks.get(taskIndex).getStatusIcon());
            } else {
                corgiPrint("Sorry, but you are out of bounds!");
            }
        }
    }

    public static void unmarkTask(String input) {
        if (!(input.split(" ").length == 1)) {
            corgiGuide();
        }
        else {
            int taskIndex = Integer.parseInt(input) - 1;
            if (taskIndex >= 0 && taskIndex <= tasks.size()) {
                tasks.get(taskIndex).isDone = false;
                corgiPrint("Okay. I 've marked this task as undone:\n"
                        + indent + tasks.get(taskIndex).getStatusIcon());
            } else {
                System.out.println("Sorry, but you are out of bounds!");
            }
        }
    }
}


