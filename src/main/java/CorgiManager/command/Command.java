package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Storage storage);
    public abstract boolean isExit();
}
