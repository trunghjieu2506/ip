package CorgiManager.storage;

import CorgiManager.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.FieldPosition;
import java.util.ArrayList;

public class Storage {
    private static String filePath;
    private static File file;
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
//            System.out.println("Here are your tasks:");
//            listTask();
            return (ArrayList<Task>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return new ArrayList<Task>();
    }

    public void saveTasks(ArrayList<Task> taskList) {
        file.getParentFile().mkdirs();
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(taskList);
            System.out.println("Tasks saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

}
