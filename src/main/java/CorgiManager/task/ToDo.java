package CorgiManager.task;

public class ToDo extends Task{
    private static final long serialVersionUID = 1L;
    public ToDo(String name){
        super(name);
    }

    @Override
    public String getStatusIcon() {
        return "[T] " + super.getStatusIcon();
    }
}
