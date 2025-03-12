package CorgiManager.task;
import java.io.Serializable;

public class Task implements Serializable {
    protected String name;
    protected boolean isDone;
    private static final long serialVersionUID = 1L;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getName() {
        return name;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ") + name;
    }
}
