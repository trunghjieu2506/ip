package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.task.ToDo;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

public class MarkCommand extends Command{
    private int taskId;
    public MarkCommand(int taskId){
        this.taskId = taskId;
    }
    @Override
    public void execute (TaskList taskList, Storage storage){
        ArrayList<Task> listTask = taskList.getTaskList();
        listTask.get(taskId).setDone(true);
        storage.saveTasks(listTask);
        Ui.corgiPrint("Nice. I 've marked this task as done:\n"
                + Ui.indent + listTask.get(taskId).getStatusIcon());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
