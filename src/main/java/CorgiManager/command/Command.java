package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.tasklist.TaskList;

/**
 * Abstract parent class for all command classes
 * Provides implementations of command execution and command signal for the application to exit.
 */
public abstract class Command {
    /**
     * Executes the specific action defined by the concrete command implementation.
     *
     * @param tasks The task list that the command operates on.
     * @param storage The storage handler responsible for saving task data.
     */
    public abstract void execute(TaskList tasks, Storage storage);
    public abstract boolean isExit();
}
