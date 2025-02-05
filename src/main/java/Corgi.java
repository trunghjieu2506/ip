import java.util.ArrayList;
import java.util.Scanner;

public class Corgi {
    public static String partition = "************************";
    public static String indent = "     ";

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello! I'm Corgi");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            String[] parts = input.split(" ", 2);

            if (input.equalsIgnoreCase("bye")) {
                corgiPrint("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                listTask();
            }
            else if (input.startsWith("mark")) {
                markTask(parts[1]);
            }
            else if (input.startsWith("unmark")) {
                unmarkTask(parts[1]);
            }
            else if (input.startsWith("todo")) {
                addTask(parts[1]);
            }
            else if (input.startsWith("deadline")) {
                addDeadline(parts[1]);
            }
            else if (input.startsWith("event")) {
                addEvent(parts[1]);
            }
            else {
                corgiGuide();
            }
        }
    }

    public static void corgiPrint(String input) {
        System.out.println(indent + partition);
        System.out.println(indent + input);
        System.out.println(indent + partition);
    }

    public static void corgiGuide() {
        corgiPrint("Incomplete command. Please refer to this guide:\n"
        + "-- todo <task_name>: add a task to the list\n"
        + "-- list: show list of tasks\n"
        + "-- mark <task_number>: mark a task\n"
        + "-- unmark <task_number>: unmark a task\n"
        + "-- deadline <task_name> /by <date>: create a task with deadline\n"
        + "-- event <event_name> /from <start_date_time> /to <end_date_time>: create an event \n");
    }

    public static void listTask() {
        System.out.println(indent + partition);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(indent + (i + 1) + "." + tasks.get(i).getStatusIcon());
        }
        System.out.println(indent + partition);
    }

    public static void addTask(String input) {
        ToDo task = new ToDo(input);
        tasks.add(task);
        corgiPrint("Added:\n"
        + indent + task.getStatusIcon() +"\n"
        + indent + "You have " + tasks.size() + " tasks in the list\n");
    }

    public static void addDeadline(String input) {
        int idx = input.indexOf("/by");
        String deadlineName = input.substring(0, idx).trim();
        // split the input into two parts (by and ...)
        String deadlineTime = input.substring(idx + 1).trim().split(" ", 2)[1];

        Deadline deadline = new Deadline(deadlineName, deadlineTime);
        tasks.add(deadline);
        corgiPrint("Added:\n"
        + indent + deadline.getStatusIcon() +"\n"
        + indent + "You have " + tasks.size() + " tasks in the list\n");
    }

    public static void addEvent(String input) {
        int idx_start = input.indexOf("/from");
        int idx_end = input.indexOf("/to");
        String eventName = input.substring(0, idx_start).trim();
        String eventStartTime = input.substring(idx_start + 1, idx_end).trim();
        eventStartTime = eventStartTime.split(" ")[1];
        // split the input into two parts (by and ...)
        String eventEndTime = input.substring(idx_end).split(" ", 2)[1];

        Event event = new Event(eventName, eventStartTime, eventEndTime);
        tasks.add(event);
        corgiPrint("Added:\n"
                + indent + event.getStatusIcon() +"\n"
                + indent + "You have " + tasks.size() + " tasks in the list\n");
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


