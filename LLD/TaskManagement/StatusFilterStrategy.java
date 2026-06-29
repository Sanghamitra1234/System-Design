import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatusFilterStrategy implements TaskFilterStrategy {

    private TaskStatus status;
    private Map<String, Task> tasks;
    
    public StatusFilterStrategy(TaskStatus status, Map<String, Task> tasks) {
        this.status = status;
        this.tasks = tasks;
    }
    
    @Override
    public List<Task> getFilteredResults() {
        return tasks.values()
        .stream()
        .filter(task -> task.getStatus() == status)
        .collect(Collectors.toList());
    }
}
