import java.util.ArrayList;
import java.util.Scanner;

public class Corgi {
    public static String partition = "************************";
    public static String indent = "     ";

    public static ArrayList<String> text_storage = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("Hello! I'm Corgi");
        System.out.println("What can I do for you?");
        System.out.println(partition);

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                corgi_print("Bye. Hope to see you again soon!");
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                show_text_storage();
            }
            else {
                text_storage.add(input);
                corgi_print("added: " + input);
            }
        }
    }

    public static void corgi_print(String input) {
        System.out.println(indent + partition);
        System.out.println(indent + input);
        System.out.println(indent + partition);
    }

    public static void show_text_storage() {
        System.out.println(indent + partition);
        for (int i = 0; i < text_storage.size(); i++) {
            System.out.println(indent + (i + 1) + "." + text_storage.get(i));
        }
        System.out.println(indent + partition);
    }
}
