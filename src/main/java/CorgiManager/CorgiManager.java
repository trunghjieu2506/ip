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

import static CorgiManager.ui.Ui.corgiWelcome;

public class CorgiManager {
    private final Storage storage;
    private TaskList taskList;

    public CorgiManager(String filePath){
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.corgiPrint(e.getMessage());
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        corgiWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);  //parse return a Command object
                c.execute(taskList, storage);
                isExit = c.isExit();
            } catch (IncorrectFormatException e) {
                Ui.corgiPrint(e.getMessage());
                Ui.corgiGuide();
            } catch (NumberFormatException e) {
                Ui.corgiPrint("Incorrect command format: " + e.getMessage());
                Ui.corgiGuide();
            } catch (Exception e) {
                Ui.corgiPrint(e.getMessage());
            }
            }
        }

    private static final String LIST_FILE = System.getProperty("user.home")
            + File.separator + ".corgimanager" + File.separator + "tasks.dat"; //create a hidden corgimanager folder in user's home folder

    public static void main(String[] args) {
        new CorgiManager(LIST_FILE).run();
    }
}


