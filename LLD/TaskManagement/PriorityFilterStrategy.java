import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriorityFilterStrategy implements TaskFilterStrategy {

    private TaskPriority priority;
    private Map<String, Task> tasks;

    public PriorityFilterStrategy(TaskPriority priority, Map<String, Task> tasks) {
        this.priority = priority;
        this.tasks = tasks;
    }

    @Override
    public List<Task> getFilteredResults() {
        return tasks.values()
                .stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }
}
