package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String commandName;
    public FindCommand(String commandName) {
        this.commandName = commandName;
    }
    @Override
    public void execute(TaskList taskList, Storage storage){
        boolean isFound = false;
        System.out.println(Ui.indent + Ui.partition);
        ArrayList<String> outputPrint = new ArrayList<>();
        outputPrint.add(Ui.indent + "Here are the matching tasks in your list:");
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
