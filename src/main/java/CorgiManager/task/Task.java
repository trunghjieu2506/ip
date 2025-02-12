package CorgiManager.task;

public class Task {
    protected String name;
    public boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
