package CorgiManager.command;

import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.storage.Storage;
import CorgiManager.task.Deadline;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public class DeadlineCommand extends Command{
    private String input;
    public DeadlineCommand(String input){
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Storage storage){
        // handle unchecked exceptions
        int idx = input.indexOf("/by");
        String deadlineName = input.substring(0, idx).trim();
        // split the input into two parts (by and ...)
        String deadlineTime = input.substring(idx + 1).trim().split(" ", 2)[1];

        Deadline deadline = new Deadline(deadlineName, deadlineTime);
        taskList.add(deadline);
        storage.saveTasks(taskList.getTaskList());

        Ui.corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                Ui.indent, deadline.getStatusIcon(), Ui.indent, taskList.size()));
    }

    @Override
    public boolean isExit() {return false;}
}
