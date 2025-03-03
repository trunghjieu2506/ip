package CorgiManager;

import CorgiManager.command.CommandHandler;
import CorgiManager.exception.InvalidCommandException;
import CorgiManager.exception.MissingArgumentException;
import CorgiManager.task.Deadline;
import CorgiManager.task.Event;
import CorgiManager.task.Task;
import CorgiManager.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class CorgiManager {
    public static final String partition = "************************************************************************";
    public static final String indent = "     ";
    private static final String LIST_FILE = System.getProperty("user.home")
            + File.separator + ".corgimanager" + File.separator + "tasks.dat"; //create a hidden corgimanager folder in user's home folder
    //create a hashmap to map commands to respective methods
    // CommandHandler is a functional interface to handle throwing exception for lambda function of COMMANDS hashtable
    public static final Map<String, CommandHandler> COMMANDS = new HashMap<>();

    // initialize commands
    static {
        COMMANDS.put("bye", input -> corgiPrint("Bye. Hope to see you again soon!"));
        COMMANDS.put("list", input -> listTask());
        COMMANDS.put("todo", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'todo' command");
            }
            addTask(input[1]);
        });
        COMMANDS.put("deadline", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'deadline' command");
            }
            addDeadline(input[1]);
        });
        COMMANDS.put("event", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'event' command");
            }
            addEvent(input[1]);
        });
        COMMANDS.put("delete", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'delete' command");
            }
            removeTask(input[1]);
        });
        COMMANDS.put("mark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'mark' command");
            }
            markTask(input[1]);
        });
        COMMANDS.put("unmark", input -> {
            if (input.length < 2) {
                throw new MissingArgumentException("Missing argument for 'unmark' command");
            }
            unmarkTask(input[1]);
        });
    }

    //tasks is used to store Task objects created by user
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello! I'm Corgi");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        loadTasks();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine().trim();
            String[] command = input.split(" ", 2);

            //Handle checked exceptions
            try {
                if (COMMANDS.containsKey(command[0])) {
                    //handle method is defined in CommandHandler
                    COMMANDS.get(command[0]).handle(command);
                    if (command[0].equalsIgnoreCase("bye")) {
                        break;
                    }
                } else {
                    throw new InvalidCommandException("Command does not exist!!");
                }
            } catch (MissingArgumentException e) {
                //System.err.println might be better
                corgiPrint(e.getMessage());
            } catch (InvalidCommandException e) {
                corgiPrint(e.getMessage());
                corgiGuide();
            }
        }
    }

    // corgi-style formatting
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

    public static void saveTasks(){
        File file = new File(LIST_FILE);
        file.getParentFile().mkdirs();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(tasks);
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static void loadTasks(){
        File file = new File(LIST_FILE);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            return;
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(LIST_FILE))){
            tasks = (ArrayList<Task>) in.readObject();
            System.out.println("Here are your tasks:");
            listTask();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
    }

    public static void addTask(String input) {
        ToDo task = new ToDo(input);
        tasks.add(task);
        saveTasks();

        corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                indent, task.getStatusIcon(), indent, tasks.size()));
    }

    public static void removeTask(String input) {
        try {
            int taskId = Integer.parseInt(input) - 1;
            Task removedTask = tasks.get(taskId);
            tasks.remove(taskId);
            saveTasks();
            corgiPrint(String.format("Noted. I've removed this task:\n%s%s\n%sYou now have %d tasks in the list.",
                    indent, removedTask.getStatusIcon(), indent, tasks.size()));
        } catch (NumberFormatException e) {
            corgiPrint("Incorrect command format: " + e.getMessage());
            corgiGuide();
        } catch (IndexOutOfBoundsException e) {
            corgiPrint("There is no task with id: " + input);
        }
    }

    public static void addDeadline(String input) {
        // handle unchecked exceptions
        try {
            int idx = input.indexOf("/by");
            String deadlineName = input.substring(0, idx).trim();
            // split the input into two parts (by and ...)
            String deadlineTime = input.substring(idx + 1).trim().split(" ", 2)[1];

            Deadline deadline = new Deadline(deadlineName, deadlineTime);
            tasks.add(deadline);
            saveTasks();

            corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                    indent, deadline.getStatusIcon(), indent, tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            corgiPrint("Incorrect command format");
            corgiGuide();
        }
    }

    public static void addEvent(String input) {
        // handle unchecked exceptions
        try {
            int idxStart = input.indexOf("/from");
            int idxEnd = input.indexOf("/to");

            String eventName = input.substring(0, idxStart).trim();
            String eventStartTime = input.substring(idxStart + 1, idxEnd).trim();
            eventStartTime = eventStartTime.split(" ")[1];
            String eventEndTime = input.substring(idxEnd).split(" ", 2)[1];

            Event event = new Event(eventName, eventStartTime, eventEndTime);
            tasks.add(event);
            saveTasks();

            corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                    indent, event.getStatusIcon(), indent, tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            corgiPrint("Incorrect command format");
            corgiGuide();
        }
    }

    public static void markTask(String input){
        // handle unchecked exceptions
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            tasks.get(taskIndex).setDone(true);
            saveTasks();
            corgiPrint("Nice. I 've marked this task as done:\n"
                    + indent + tasks.get(taskIndex).getStatusIcon());
        } catch (NumberFormatException e) {
            corgiPrint("Incorrect command format: " + e.getMessage());
            corgiGuide();
        } catch (IndexOutOfBoundsException e) {
            corgiPrint("There is no task with id: " + input);
            corgiGuide();
        }
    }

    public static void unmarkTask(String input){
        // handle unchecked exceptions
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            tasks.get(taskIndex).setDone(false);
            saveTasks();
            corgiPrint("Okay. I 've marked this task as undone:\n"
                    + indent + tasks.get(taskIndex).getStatusIcon());
        } catch (NumberFormatException e) {
            corgiPrint("Incorrect command format: " + e.getMessage());
            corgiGuide();
        } catch (IndexOutOfBoundsException e) {
            corgiPrint("There is no task with id: " + input);
            corgiGuide();
        }
    }
}


