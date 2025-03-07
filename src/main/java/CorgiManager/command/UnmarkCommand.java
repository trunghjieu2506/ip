package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command{
    private int taskId;
    public UnmarkCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute (TaskList taskList, Storage storage){
        ArrayList<Task> listTask = taskList.getTaskList();
        listTask.get(taskId).setDone(false);
        storage.saveTasks(listTask);
        Ui.corgiPrint("Okay. I 've marked this task as undone:\n"
                + Ui.indent + listTask.get(taskId).getStatusIcon());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
