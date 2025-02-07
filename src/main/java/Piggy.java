import java.util.Scanner;

public class Piggy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        System.out.println("____________________________________________________________");
        System.out.println(" Oink! Hello I'm Piggy! :)");
        System.out.println("            (,) ");
        System.out.println("            _/");
        System.out.println("        .--\"_\"--.");
        System.out.println("      .\"   |I|   \".");
        System.out.println("     /     |I|     \\");
        System.out.println("    /      |I|      \\");
        System.out.println("   ;       |I|       ;");
        System.out.println("   |   _   '\"'   _   |");
        System.out.println("  /|  |\\\\_,...,_//|  |\\");
        System.out.println(" / |  |-\" 0   0 \"-|  | \\");
        System.out.println("(//| /             \\ |\\\\)");
        System.out.println(" ^ \\ |    _..._    | / ^");
        System.out.println("    '|  .'     '.  |'");
        System.out.println("     \\  | () () |  /");
        System.out.println("    / '..     .'.' \\");
        System.out.println("   /' / \\\"\"\"/ \\ \\");
        System.out.println("  (/ /    \"\"\"    \\ \\)");
        System.out.println("   ^               ^");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                taskList.listTasks();
            } else if (userInput.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    taskList.markTask(index, true);
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.markTask(index, false);
                } catch (NumberFormatException e) {
                    System.out.println(" Please enter a valid number.");
                }
            } else if (userInput.startsWith("todo ")) {
                taskList.addTask(new ToDo(userInput.substring(5)));
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split(" /by ", 2);
                if (parts.length < 2) {
                    System.out.println(" Invalid format! Use: deadline <description> /by <time>");
                } else {
                    taskList.addTask(new Deadline(parts[0], parts[1]));
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split(" /from | /to ", 3);
                if (parts.length < 3) {
                    System.out.println(" Invalid format! Use: event <description> /from <start> /to <end>");
                } else {
                    taskList.addTask(new Event(parts[0], parts[1], parts[2]));
                }
            } else {
                System.out.println(" Unknown command.");
            }
        }

        scanner.close();
    }
}
