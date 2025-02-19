package corgimanager.task;

public class Deadline extends Task{
    private static final long serialVersionUID = 1L;
    protected String by;
    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String getStatusIcon() {
        return "[D] " + super.getStatusIcon() + "(by: " + by + ")";
    }
}
