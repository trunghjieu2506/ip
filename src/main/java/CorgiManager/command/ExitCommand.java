package CorgiManager.command;
import CorgiManager.storage.Storage;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage){
        Ui.corgiPrint("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
