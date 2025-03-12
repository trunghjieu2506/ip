package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

/**
 * Searches the task list for tasks matching a given keyword and display them.
 */
public class FindCommand extends Command{
    private String commandName;
    public FindCommand(String commandName) {
        this.commandName = commandName;
    }

    /**
     * Searches the provided task list for tasks containing the specified keyword and displays
     * matching tasks. If no tasks match the keyword, an appropriate message is shown.
     *
     * @param taskList The TaskList instance containing tasks to search.
     * @param storage  The Storage instance, not directly used in this command but maintained for consistency.
     */
    @Override
    public void execute(TaskList taskList, Storage storage){
        boolean isFound = false;
        System.out.println(Ui.indent + Ui.partition);
        // outputPrint stores the output if isFound is true
        ArrayList<String> outputPrint = new ArrayList<>();
        outputPrint.add(Ui.indent + "Here are the matching tasks in your list:");

        //Loop through the taskList and check each task's name whether it contains commandName
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTaskList().get(i).getName().contains(commandName)) {
                isFound = true;
                outputPrint.add(Ui.indent + (i + 1) + "." + taskList.getTaskList().get(i).getStatusIcon());
            }
        }
        if (!isFound) {
            System.out.println(Ui.indent + "Unable to find any matching tasks in your list.");
        } else {
            for (String i : outputPrint) {
                System.out.println(i);
            }
        }
        System.out.println(Ui.indent + Ui.partition);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
