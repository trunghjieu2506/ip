package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command{
    private String input;
    public UnmarkCommand(String input){
        this.input = input;
    }

    @Override
    public void execute (TaskList taskList, Storage storage){
        ArrayList<Task> listTask = taskList.getTaskList();
        int taskIndex = Integer.parseInt(input) - 1;
        listTask.get(taskIndex).setDone(false);
        storage.saveTasks(listTask);
        Ui.corgiPrint("Okay. I 've marked this task as undone:\n"
                + Ui.indent + listTask.get(taskIndex).getStatusIcon());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
