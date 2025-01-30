import java.util.Scanner;

public class Corgi {
    public static String partition = "************";
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
            corgi_print(input);
        }
    }

    public static void corgi_print(String input) {
        String indent = "     ";
        System.out.println(partition);
        System.out.println(indent + input);
        System.out.println(partition);

    }
}
