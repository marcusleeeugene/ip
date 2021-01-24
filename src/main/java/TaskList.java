import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
        taskList.add(null);
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public void remove(Task task) {
        taskList.remove(task);
    }

    public Task get(int i) {
        return taskList.get(i);
    }


}