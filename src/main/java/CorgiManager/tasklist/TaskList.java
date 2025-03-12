package CorgiManager.tasklist;

import CorgiManager.task.Task;
import java.util.ArrayList;

/**
 * TaskList Class stores the user's list of tasks
 * Provides a single source of data across the application
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void add(Task newTask) {
        taskList.add(newTask);
    }

    public int size() {
        return taskList.size();
    }
}
