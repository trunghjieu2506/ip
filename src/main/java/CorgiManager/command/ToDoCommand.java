package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.ToDo;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public class ToDoCommand extends Command{
    private String input;
    public ToDoCommand(String input){
        this.input = input;
    }
    @Override
    public void execute (TaskList taskList, Storage storage){
        ToDo task = new ToDo(input);
        taskList.add(task);
        storage.saveTasks(taskList.getTaskList());

        Ui.corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                Ui.indent, task.getStatusIcon(), Ui.indent, taskList.size()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
