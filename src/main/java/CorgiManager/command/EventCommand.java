package CorgiManager.command;

import CorgiManager.storage.Storage;
import CorgiManager.task.Event;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;

public class EventCommand extends Command{
    private String eventName;
    private String eventStartTime;
    private String eventEndTime;
    public EventCommand(String eventName, String eventStartTime, String eventEndTime) {
        this.eventName = eventName;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
    }

    @Override
    public void execute(TaskList taskList, Storage storage){
        Event event = new Event(eventName, eventStartTime, eventEndTime);
        taskList.add(event); // high-level logic
        storage.saveTasks(taskList.getTaskList()); //high-level logic

        Ui.corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                Ui.indent, event.getStatusIcon(), Ui.indent, taskList.size()));
    }

    @Override
    public boolean isExit() {return false;}
}
