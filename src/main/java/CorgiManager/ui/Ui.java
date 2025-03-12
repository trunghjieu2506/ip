package CorgiManager.ui;

import java.util.Scanner;

/**
 * User Interface Class
 * Responsible for formatting the program and receiving user input
 */
public class Ui {
    public static final String partition = "--------------------------------------------------------------------------";
    public static final String indent = "     ";

    public static void corgiWelcome(){
        System.out.println("Hello! I'm Corgi");
        System.out.println("What can I do for you?");
        System.out.println(partition);
    }

    public static void corgiPrint(String input) {
        System.out.println(indent + partition);
        System.out.println(indent + input);
        System.out.println(indent + partition);
    }

    public static void corgiGuide() {
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

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
