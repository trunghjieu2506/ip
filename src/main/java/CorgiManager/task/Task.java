package CorgiManager.task;

import java.io.Serializable;

/**
 * Represents a generic task within the CorgiManager application.
 * Stores information about the task's name and completion status.
 * Implements Serializable to allow tasks to be saved and loaded from persistent storage.
 */
public class Task implements Serializable {
    protected String name;
    protected boolean isDone;

    private static final long serialVersionUID = 1L;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
    public String getName() {
        return name;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}