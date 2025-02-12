package CorgiManager.task;

public class ToDo extends Task{
    public ToDo(String name){
        super(name);
    }

    @Override
    public String getStatusIcon() {
        return "[T] " + super.getStatusIcon();
    }
}
