package corgimanager.task;

public class Event extends Task{
    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to=to;
    }

    @Override
    public String getStatusIcon() {
        return "[E] " + super.getStatusIcon() + "(from: " + from + " to: " + to + ")";
    }
}
