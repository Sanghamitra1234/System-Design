import java.time.LocalDateTime;
import java.util.List;

public class StrategyPatternDemo {
    public static void main(String[] args) {
        System.out.println("========== Strategy Pattern Demo ==========\n");

        TaskManagementSystem system = TaskManagementSystem.getInstance();

        // Create users
        User alice = system.createUser("Alice Johnson", "alice@example.com");
        User bob = system.createUser("Bob Smith", "bob@example.com");
        User charlie = system.createUser("Charlie Brown", "charlie@example.com");

        System.out.println("1. Creating Tasks with Different Priorities and Statuses...\n");

        // Create tasks
        Task task1 = system.createTask(
            "Design Database",
            "Create ER diagram",
            LocalDateTime.now().plusDays(5),
            TaskPriority.HIGH,
            TaskStatus.IN_PROGRESS,
            alice
        );

        Task task2 = system.createTask(
            "Implement APIs",
            "Build REST endpoints",
            LocalDateTime.now().plusDays(7),
            TaskPriority.CRITICAL,
            TaskStatus.TODO,
            bob
        );

        Task task3 = system.createTask(
            "Write Tests",
            "Unit tests for all components",
            LocalDateTime.now().plusDays(10),
            TaskPriority.MEDIUM,
            TaskStatus.TODO,
            charlie
        );

        Task task4 = system.createTask(
            "Code Review",
            "Review pull requests",
            LocalDateTime.now().plusDays(2),
            TaskPriority.CRITICAL,
            TaskStatus.IN_PROGRESS,
            alice
        );

        Task task5 = system.createTask(
            "Documentation",
            "Write API documentation",
            LocalDateTime.now().plusDays(8),
            TaskPriority.LOW,
            TaskStatus.BLOCKED,
            bob
        );

        System.out.println("✓ Created 5 tasks\n");

        System.out.println("========== Strategy Pattern in Action ==========\n");

        // Strategy 1: Filter by Status
        System.out.println("2. Using StatusFilterStrategy - Get all TODO tasks:");
        List<Task> todoTasks = system.getTaskByStatus(TaskStatus.TODO);
        System.out.println("   Found " + todoTasks.size() + " TODO tasks:");
        for (Task task : todoTasks) {
            System.out.println("   - " + task.getTitle() + " (Priority: " + task.getPriority() + ")");
        }
        System.out.println();

        // Strategy 2: Filter by User
        System.out.println("3. Using UserFilterStrategy - Get all tasks assigned to Alice:");
        List<Task> aliceTasks = system.getTaskByUser(alice.getUserId());
        System.out.println("   Found " + aliceTasks.size() + " tasks assigned to Alice:");
        for (Task task : aliceTasks) {
            System.out.println("   - " + task.getTitle() + " (Status: " + task.getStatus() + ")");
        }
        System.out.println();

        // Strategy 3: Filter by Priority
        System.out.println("4. Using PriorityFilterStrategy - Get all CRITICAL priority tasks:");
        List<Task> criticalTasks = system.getTaskByPriority(TaskPriority.CRITICAL);
        System.out.println("   Found " + criticalTasks.size() + " CRITICAL tasks:");
        for (Task task : criticalTasks) {
            System.out.println("   - " + task.getTitle() + " (Assigned to: " + task.getAssigneeUserId().getName() + ")");
        }
        System.out.println();

        // Strategy 4: Filter by Status (IN_PROGRESS)
        System.out.println("5. Using StatusFilterStrategy - Get all IN_PROGRESS tasks:");
        List<Task> inProgressTasks = system.getTaskByStatus(TaskStatus.IN_PROGRESS);
        System.out.println("   Found " + inProgressTasks.size() + " IN_PROGRESS tasks:");
        for (Task task : inProgressTasks) {
            System.out.println("   - " + task.getTitle() + " (Assigned to: " + task.getAssigneeUserId().getName() + ")");
        }
        System.out.println();

        // Strategy 5: Search (not using strategy, but shown for comparison)
        System.out.println("6. Using searchTask - Search for tasks containing 'API':");
        List<Task> searchResults = system.searchTask("API");
        System.out.println("   Found " + searchResults.size() + " matching tasks:");
        for (Task task : searchResults) {
            System.out.println("   - " + task.getTitle());
        }
        System.out.println();

        System.out.println("========== Strategy Pattern Benefits ==========");
        System.out.println("✓ Easy to add new filter strategies (e.g., DateRangeFilterStrategy)");
        System.out.println("✓ Each strategy is independent and reusable");
        System.out.println("✓ No need to modify existing code when adding new filters");
        System.out.println("✓ Follows Open/Closed Principle");
        System.out.println("✓ Clean separation of concerns\n");

        System.out.println("========== How to Add a New Strategy ==========");
        System.out.println("1. Create a new class implementing TaskFilterStrategy");
        System.out.println("2. Implement getFilteredResults() method");
        System.out.println("3. Add a method in TaskManagementSystem to use the new strategy");
        System.out.println("4. No changes needed to existing strategies!\n");

        System.out.println("========== Demo Completed! ==========");
    }
}
