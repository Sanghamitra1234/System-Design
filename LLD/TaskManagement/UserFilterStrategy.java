import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserFilterStrategy implements TaskFilterStrategy {

    private User user;
    private Map<String, Task> tasks;
    
    public UserFilterStrategy(User user, Map<String, Task> tasks) {
        this.user = user;
        this.tasks = tasks;
    }
    
    @Override
    public List<Task> getFilteredResults() {
        return tasks.values().stream()
        .filter(task -> task.getAssigneeUserId() == user)
        .collect(Collectors.toList());
    }
}

