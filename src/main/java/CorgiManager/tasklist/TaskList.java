package CorgiManager.tasklist;

import CorgiManager.task.Task;
import java.util.ArrayList;

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
