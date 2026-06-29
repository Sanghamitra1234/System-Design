import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManagementSystem {
    private static TaskManagementSystem instance;
    private final Map<String, User> users;
    private final Map<String, Task> tasks;
    private final Map<String, TaskList> taskLists;
    private TaskFilterStrategy taskFilterStrategy;
    
    private TaskManagementSystem() {
        users = new HashMap<>();
        tasks = new HashMap<>();
        taskLists = new HashMap<>();
        taskFilterStrategy = null;
    }
    
    public static synchronized TaskManagementSystem getInstance() {
        if (instance == null) {
            instance = new TaskManagementSystem();
        }
        return instance;
    }

    public synchronized User createUser( String name, String email) {
        User user = new User(name, email);
        users.put(user.getUserId(), user);
        return user;
    }

    public synchronized Task createTask(String title, String description, 
                LocalDateTime dueDate, TaskPriority priority, TaskStatus status, 
                User assigneeUserId) {
        Task task = new Task.Builder()
                .setTitle(title)
                .setDescription(description)
                .setDueDate(dueDate)
                .setPriority(priority)
                .setStatus(status)
                .setAssignee(assigneeUserId)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .build();
        tasks.put(task.getTaskId(), task);
        return task;
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    public List<Task> getTaskByStatus(TaskStatus status) {
        taskFilterStrategy = new StatusFilterStrategy(status, this.tasks);
        return taskFilterStrategy.getFilteredResults();
    }

    public List<Task> getTaskByUser(String userId) {
        taskFilterStrategy = new UserFilterStrategy(users.get(userId), this.tasks);
        return taskFilterStrategy.getFilteredResults();
    }

    public List<Task> getTaskByPriority(TaskPriority priority) {
        taskFilterStrategy = new PriorityFilterStrategy(priority, this.tasks);
        return taskFilterStrategy.getFilteredResults();
    }

    public List<Task> searchTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
       for (Task task: tasks.values()) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
       }
        return matchingTasks;
    }
}
