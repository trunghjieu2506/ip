package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.task.ToDo;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command{
    private String input;
    public MarkCommand(String input){
        this.input = input;
    }
    @Override
    public void execute (TaskList taskList, Storage storage){
        ArrayList<Task> listTask = taskList.getTaskList();
        int taskIndex = Integer.parseInt(input) - 1;
        listTask.get(taskIndex).setDone(true);
        storage.saveTasks(listTask);
        Ui.corgiPrint("Nice. I 've marked this task as done:\n"
                + Ui.indent + listTask.get(taskIndex).getStatusIcon());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
