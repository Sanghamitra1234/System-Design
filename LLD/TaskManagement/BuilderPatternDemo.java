import java.time.LocalDateTime;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        System.out.println("========== Builder Pattern Demo ==========\n");

        // Create some users
        User alice = new User("Alice Johnson", "alice@example.com");
        User bob = new User("Bob Smith", "bob@example.com");

        System.out.println("1. Creating Task WITHOUT Builder (Old Way - Hard to Read):");
        System.out.println("Code: new Task(title, description, dueDate, priority, status, assignee, ...)");
        System.out.println("Problem: Too many parameters, hard to remember order, error-prone\n");

        System.out.println("2. Creating Task WITH Builder (New Way - Clean & Readable):\n");

        // Example 1: Basic task creation
        System.out.println("Example 1: Basic Task");
        Task task1 = new Task.Builder()
            .setTitle("Design Database Schema")
            .setDescription("Create ER diagram and design the database schema")
            .setDueDate(LocalDateTime.now().plusDays(5))
            .setPriority(TaskPriority.HIGH)
            .setAssignee(alice)
            .build();

        System.out.println("✓ Task Created:");
        System.out.println("  Title: " + task1.getTitle());
        System.out.println("  Priority: " + task1.getPriority());
        System.out.println("  Status: " + task1.getStatus());
        System.out.println("  Assignee: " + task1.getAssigneeUserId().getName() + "\n");

        // Example 2: Task with tags
        System.out.println("Example 2: Task with Tags");
        Tag designTag = new Tag("design", "Design");
        Tag databaseTag = new Tag("database", "Database");

        Task task2 = new Task.Builder()
            .setTitle("Implement REST APIs")
            .setDescription("Build REST endpoints for task CRUD operations")
            .setDueDate(LocalDateTime.now().plusDays(7))
            .setPriority(TaskPriority.CRITICAL)
            .setStatus(TaskStatus.IN_PROGRESS)
            .setAssignee(bob)
            .addTag(designTag)
            .addTag(databaseTag)
            .build();

        System.out.println("✓ Task Created with Tags:");
        System.out.println("  Title: " + task2.getTitle());
        System.out.println("  Priority: " + task2.getPriority());
        System.out.println("  Status: " + task2.getStatus());
        System.out.println("  Tags: " + task2.getTags().size());
        for (Tag tag : task2.getTags()) {
            System.out.println("    - " + tag.getName());
        }
        System.out.println();

        // Example 3: Minimal task (using defaults)
        System.out.println("Example 3: Minimal Task (Using Defaults)");
        Task task3 = new Task.Builder()
            .setTitle("Write Documentation")
            .setDescription("Document all API endpoints")
            .build();

        System.out.println("✓ Task Created with Defaults:");
        System.out.println("  Title: " + task3.getTitle());
        System.out.println("  Priority: " + task3.getPriority() + " (default)");
        System.out.println("  Status: " + task3.getStatus() + " (default)");
        System.out.println("  Assignee: " + (task3.getAssigneeUserId() != null ? task3.getAssigneeUserId().getName() : "Unassigned") + "\n");

        // Example 4: Complex task with multiple configurations
        System.out.println("Example 4: Complex Task with Multiple Configurations");
        Task task4 = new Task.Builder()
            .setTitle("Setup CI/CD Pipeline")
            .setDescription("Setup continuous integration and deployment pipeline")
            .setDueDate(LocalDateTime.now().plusDays(3))
            .setPriority(TaskPriority.CRITICAL)
            .setStatus(TaskStatus.BLOCKED)
            .setAssignee(alice)
            .addTag(new Tag("devops", "DevOps"))
            .addTag(new Tag("infrastructure", "Infrastructure"))
            .setCreatedAt(LocalDateTime.now().minusDays(2))
            .setUpdatedAt(LocalDateTime.now())
            .build();

        System.out.println("✓ Complex Task Created:");
        System.out.println("  Title: " + task4.getTitle());
        System.out.println("  Priority: " + task4.getPriority());
        System.out.println("  Status: " + task4.getStatus());
        System.out.println("  Assignee: " + task4.getAssigneeUserId().getName());
        System.out.println("  Tags: " + task4.getTags().size());
        System.out.println("  Created: " + task4.getCreatedAt());
        System.out.println("  Updated: " + task4.getUpdatedAt() + "\n");

        System.out.println("========== Builder Pattern Benefits ==========");
        System.out.println("✓ Readable: Clear what each parameter is");
        System.out.println("✓ Flexible: Set only required fields");
        System.out.println("✓ Maintainable: Easy to add new fields");
        System.out.println("✓ Safe: Validation in build() method");
        System.out.println("✓ Chainable: Fluent interface (method chaining)");
        System.out.println("✓ Defaults: Sensible default values provided\n");

        System.out.println("========== Demo Completed! ==========");
    }
}
