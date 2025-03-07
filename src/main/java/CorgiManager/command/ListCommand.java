package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList taskList, Storage storage) {
        System.out.println(Ui.indent + Ui.partition);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(Ui.indent + (i + 1) + "." + taskList.getTaskList().get(i).getStatusIcon());
        }
        System.out.println(Ui.indent + Ui.partition);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
