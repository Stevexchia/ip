package piggy.util;

public class Constants {
    public static final String SEPARATOR = "____________________________________________________________";
    public static final String PIG_INTRO = """
             Oink! Welcome to Piggy's Task Manager! 🐷
             
                         (,) 
                         _/
                    .--\"_\"--.
                  .\"   |I|   \".
                 /     |I|     \\
                /      |I|      \\
                ;       |I|       ;
               |   _   '\"'   _   |
              /|  |\\\\_,...,_//|  |\\
             / |  |-\" 0   0 \"-|  | \\
            (//| /             \\ |\\\\)
              ^ \\ |    _..._    | / ^
                 '|  .'     '.  |'
                  \\  | () () |  /
                 / '..     .'.' \\
                /' / \\\"\"\"/ \\ \\
               (/ /    \"\"\"    \\ \\)
                ^               ^
                
            Let's get muddy with some tasks! """;

    //commands
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";

    //others
    public static final String BYE_MESSAGE = "Oink, oink! I'm going for a nap in the mud. See you soon! \uD83D\uDC16";

    // General
    public static final String SPACE = " ";
    public static final int MAX_ARGUMENTS_LENGTH = 1;  // For command parsing
    public static final String UNKNOWN_COMMAND_MESSAGE = "Oink, oink! Unknown command!";
}
