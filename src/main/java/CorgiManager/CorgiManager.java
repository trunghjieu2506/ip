package CorgiManager;

import CorgiManager.command.Command;
import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.parser.Parser;
import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;
import java.io.*;

/**
 * Main class of the CorgiManager application.
 * Responsible for initializing the application, managing tasks, storing data
 * and interacting with the user through a command-line interface.
 */
public class CorgiManager {

    /** Storage component responsible for reading and writing task data. */
    private final Storage storage;

    /** TaskList component that manages a list of user tasks. */
    private TaskList taskList;

    /**
     * Constructs a CorgiManager instance with the specified file path for storage.
     * Loads TaskList from storage or creates a new empty list upon failure.
     *
     * @param filePath Path to the storage file.
     */
    public CorgiManager(String filePath){
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.corgiPrint(e.getMessage());
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Runs the main application loop.
     * Continuously reads user commands, parses them, and executes the corresponding actions
     * until the ExitCommand is issued.
     */
    public void run() {
        Ui.corgiWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(taskList, storage);
                isExit = c.isExit();
            } catch (IncorrectFormatException e) {
                Ui.corgiPrint(e.getMessage());
            } catch (NumberFormatException e) {
                Ui.corgiPrint("Incorrect command syntax. Command expects a number");
            } catch (IndexOutOfBoundsException e) {
                Ui.corgiPrint("Index out of bounds of task list");
            } catch (Exception e) {
                Ui.corgiPrint(e.getMessage());
            }
        }
    }

    /**
     * Default path for storing the task list file.
     * Creates a hidden directory named '.corgimanager' in the user's home folder
     * and stores tasks in 'tasks.dat'.
     */
    private static final String LIST_FILE = System.getProperty("user.home")
            + File.separator + ".corgimanager" + File.separator + "tasks.dat";

    public static void main(String[] args) {
        new CorgiManager(LIST_FILE).run();
    }
}



