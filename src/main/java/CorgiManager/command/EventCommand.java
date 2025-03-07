package CorgiManager.command;

import CorgiManager.exception.IncorrectFormatException;
import CorgiManager.storage.Storage;
import CorgiManager.task.Event;
import CorgiManager.tasklist.TaskList;
import CorgiManager.ui.Ui;
import CorgiManager.ui.Ui.*;

import static CorgiManager.CorgiManager.saveTasks;
import static CorgiManager.ui.Ui.corgiPrint;
import static CorgiManager.ui.Ui.indent;

public class EventCommand extends Command{
    private String input;
    public EventCommand(String input){
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Storage storage){
        // handle unchecked exceptions
        int idxStart = input.indexOf("/from");
        int idxEnd = input.indexOf("/to");

        String eventName = input.substring(0, idxStart).trim();
        String eventStartTime = input.substring(idxStart + 1, idxEnd).trim();
        eventStartTime = eventStartTime.split(" ")[1];
        String eventEndTime = input.substring(idxEnd).split(" ", 2)[1];

        Event event = new Event(eventName, eventStartTime, eventEndTime);
        taskList.add(event);
        saveTasks();

        Ui.corgiPrint(String.format("Noted. I've added this task:\n%s%s\n%sYou now have %d tasks in the list.",
                Ui.indent, event.getStatusIcon(), Ui.indent, taskList.size()));
    }

    @Override
    public boolean isExit() {return false;}
}
