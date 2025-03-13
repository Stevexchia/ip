package piggy.util;

public class Constants {
    // UI-related constants
    public static final String SEPARATOR = "____________________________________________________________";
    public static final String PIG_INTRO = """
             Oink! Welcome to Piggy's Task Manager! 🐷
             
                         (,)
                         _/
                    .--"_"--.
                  ."   |I|   ".
                 /     |I|     \\
                /      |I|      \\
                ;       |I|       ;
               |   _   '"'   _   |
              /|  |\\\\_,...,_//|  |\\
             / |  |-" 0   0 "-|  | \\
            (//| /             \\ |\\\\)
              ^ \\ |    _..._    | / ^
                 '|  .'     '.  |'
                  \\  | () () |  /
                 / '..     .'.' \\
                /' / \\""\"/ \\ \\
               (/ /    ""\"    \\ \\)
                ^               ^
                
            Let's get muddy with some tasks! """;

    // Command-related constants
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String MARK_COMMAND = "mark";
    public static final String UNMARK_COMMAND = "unmark";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";

    // Message-related constants
    public static final String BYE_MESSAGE = "Oink, oink! I'm going for a nap in the mud. See you soon! \uD83D\uDC16";
    public static final String UNKNOWN_COMMAND_MESSAGE = "Oink, oink! Unknown command!";
    public static final String INVALID_TASK_NUMBER_MESSAGE = "Oops, that task number doesn’t exist in my mud!";
    public static final String INVALID_FORMAT_TODO_MESSAGE = "OOPS!!! The description of a todo cannot be empty, piggy snout!";
    public static final String INVALID_FORMAT_DEADLINE_MESSAGE = "Oink! Invalid format! Use: deadline <description> /by <time>";
    public static final String INVALID_FORMAT_EVENT_MESSAGE = "Oink! Invalid format! Use: event <description> /from <start> /to <end>";
    public static final String TASK_ADDED_MESSAGE = "Oink! This task is now in my snout:";
    public static final String TASK_DELETED_MESSAGE = "Snort! This task is forgotten in the haystack:";
    public static final String TASKS_COUNT_MESSAGE = "Snort! You’ve got %d tasks in your pen.";
    public static final String TASK_MARKED_MESSAGE = "Nice! I've marked this task as done:";
    public static final String TASK_UNMARKED_MESSAGE = "Okay! I've unmarked this task:";
    public static final String NO_TASKS_MESSAGE = "Oink... No tasks in the mud here!";
    public static final String TASKS_LIST_HEADER = "Here’s what’s on my mind, oink oink!";
    public static final String INVALID_FIND_COMMAND = "Oink! Please provide a keyword to search for!";
    public static final String CREATE_ERROR = "Oink! Error creating tasks file.";
    public static final String LOAD_ERROR = "Oink! Error loading tasks from file.";
    public static final String SAVE_ERROR = "Oink! Error saving tasks to file.";
    public static final String FIND_MESSAGE = "I found all of these delicious tasks!:";
    public static final String INVALID_FIND_MESSAGE = "Oink... No tasks match the keyword '";

    // General constants
    public static final String SPACE = " ";
    public static final int MAX_ARGUMENTS_LENGTH = 1;  // For command parsing
    public static final int TASK_INDEX_OFFSET = 1;    // For converting 1-based to 0-based index
}