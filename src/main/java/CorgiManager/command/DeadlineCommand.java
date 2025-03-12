package CorgiManager.command;

import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.storage.Storage;
import CorgiManager.task.Deadline;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public class DeadlineCommand extends Command{
    private String deadlineName;
    private String deadlineTime;
    public DeadlineCommand(String deadlineName, String deadlineTime){
        this.deadlineName = deadlineName;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public void execute(TaskList taskList, Storage storage){
        Deadline deadline = new Deadline(deadlineName, deadlineTime);
        taskList.add(deadline);
        storage.saveTasks(taskList.getTaskList());

        Ui.corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                Ui.indent, deadline.getStatusIcon(), Ui.indent, taskList.size()));
    }

    @Override
    public boolean isExit() {return false;}
}
