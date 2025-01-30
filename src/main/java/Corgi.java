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
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                ArrayList<String> exit_message = new ArrayList<>(1);
                exit_message.add("Bye. Hope to see you again soon!");
                corgiPrint(exit_message);
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                listTask();
            }
            else if (input.startsWith("mark ")) {
                markTask(input);
            }
            else if (input.startsWith("unmark ")) {
                unmarkTask(input);
            }
            else {
                addTask(input);
            }
        }
    }

    public static void corgiPrint(ArrayList<String> input) {
        System.out.println(indent + partition);
        for (String text : input) {
            System.out.println(indent + text);
        }
        System.out.println(indent + partition);
    }

    public static void listTask() {
        ArrayList<String> list_task = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            list_task.add(indent + (i + 1) + "." + tasks.get(i).getStatusIcon());
        }
        corgiPrint(list_task);
    }

    public static void addTask(String input) {
        tasks.add(new Task(input));
        ArrayList<String> added_task = new ArrayList<>(1);
        added_task.add("added:  "+ input);
        corgiPrint(added_task);
    }

    public static void markTask(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(taskIndex).isDone = true;
        ArrayList<String> mark_print = new ArrayList<>(1);
        if (taskIndex >= 0 && taskIndex <= tasks.size()) {
            mark_print.add("Nice. I 've marked this task as done:");
            mark_print.add(tasks.get(taskIndex).getStatusIcon());
            corgiPrint(mark_print);
        }
        else {
            System.out.println("Sorry, but you are out of bounds!");
        }
    }

    public static void unmarkTask(String input) {
        int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(taskIndex).isDone = false;
        ArrayList<String> unmark_print = new ArrayList<>(1);
        if (taskIndex >= 0 && taskIndex <= tasks.size()) {
            unmark_print.add("Okay. I 've marked this task as undone:");
            unmark_print.add(tasks.get(taskIndex).getStatusIcon());
            corgiPrint(unmark_print);
        }
        else {
            System.out.println("Sorry, but you are out of bounds!");
        }
    }
}


