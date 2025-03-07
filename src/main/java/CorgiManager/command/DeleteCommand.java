package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Task;
import CorgiManager.task.ToDo;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

import java.util.ArrayList;

public class DeleteCommand extends Command{
    private int taskId;
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute (TaskList taskList, Storage storage) {
        ArrayList<Task> listTask = taskList.getTaskList();
        Task removedTask = listTask.get(taskId);
        listTask.remove(taskId);
        storage.saveTasks(listTask);
        Ui.corgiPrint(String.format("Noted. I've removed this task:\n%s%s\n%sYou now have %d tasks in the list.",
                Ui.indent, removedTask.getStatusIcon(), Ui.indent, taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
