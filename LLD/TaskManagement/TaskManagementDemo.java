import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManagementDemo {
    public static void main(String[] args) {
        System.out.println("========== Task Management System Demo ==========\n");
        
        TaskManagementSystem system = TaskManagementSystem.getInstance();
        
        System.out.println("1. Creating Users...");
        User user1 = system.createUser("Alice Johnson", "alice@example.com");
        User user2 = system.createUser("Bob Smith", "bob@example.com");
        User user3 = system.createUser("Charlie Brown", "charlie@example.com");
        System.out.println("✓ Created users: " + user1.getName() + ", " + user2.getName() + ", " + user3.getName());
        System.out.println("  User1 ID: " + user1.getUserId() + "\n");
        
        System.out.println("2. Creating Tasks...");
        Task task1 = system.createTask(
            "Design Database Schema",
            "Create ER diagram and design the database schema for task management",
            LocalDateTime.now().plusDays(5),
            TaskPriority.HIGH,
            TaskStatus.TODO,
            user1,
            LocalDateTime.now(),
            LocalDateTime.now(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        System.out.println("✓ Task 1 Created: " + task1.getTitle());
        System.out.println("  Task ID: " + task1.getTaskId());
        System.out.println("  Status: " + task1.getStatus() + "\n");
        
        Task task2 = system.createTask(
            "Implement REST APIs",
            "Build REST endpoints for task CRUD operations",
            LocalDateTime.now().plusDays(7),
            TaskPriority.CRITICAL,
            TaskStatus.TODO,
            user2,
            LocalDateTime.now(),
            LocalDateTime.now(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        System.out.println("✓ Task 2 Created: " + task2.getTitle());
        System.out.println("  Status: " + task2.getStatus() + "\n");
        
        Task task3 = system.createTask(
            "Write Unit Tests",
            "Write comprehensive unit tests for all components",
            LocalDateTime.now().plusDays(10),
            TaskPriority.MEDIUM,
            TaskStatus.TODO,
            user3,
            LocalDateTime.now(),
            LocalDateTime.now(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );
        System.out.println("✓ Task 3 Created: " + task3.getTitle() + "\n");
        
        System.out.println("3. Adding Tags to Tasks...");
        Tag tag1 = new Tag("design", "Database Design");
        Tag tag2 = new Tag("backend", "Backend Development");
        Tag tag3 = new Tag("testing", "Testing & QA");
        
        task1.addTags(tag1);
        task2.addTags(tag2);
        task3.addTags(tag3);
        System.out.println("✓ Tags added to tasks\n");
        
        System.out.println("4. Changing Task Status...");
        task1.changeStatus(user1, TaskStatus.IN_PROGRESS);
        System.out.println("✓ Task 1 status changed to: " + task1.getStatus() + "\n");
        
        System.out.println("5. Adding Comments to Task...");
        Comment comment1 = new Comment("comment-1", user1.getUserId(), LocalDateTime.now(), 
            "Started working on the database design");
        task1.addComments(user1, comment1);
        
        Comment comment2 = new Comment("comment-2", user2.getUserId(), LocalDateTime.now(), 
            "Great! Let me know when the schema is ready");
        task1.addComments(user2, comment2);
        
        System.out.println("✓ Comments added to Task 1");
        System.out.println("  Total comments: " + task1.getComments().size() + "\n");
        
        System.out.println("6. Assigning Task to Different User...");
        task2.assignTask(user1);
        System.out.println("✓ Task 2 reassigned to: " + task2.getAssigneeUserId().getName() + "\n");
        
        System.out.println("7. Changing Task Priority...");
        task3.assignPriority(user3, TaskPriority.HIGH);
        System.out.println("✓ Task 3 priority changed to: " + task3.getPriority() + "\n");
        
        System.out.println("8. Viewing Activity Logs for Task 1...");
        List<ActivityLog> logs = task1.getActivityLogs();
        System.out.println("Total activities: " + logs.size());
        for (int i = 0; i < logs.size(); i++) {
            ActivityLog log = logs.get(i);
            System.out.println("  Activity " + (i + 1) + ": " + log.getEventType() + 
                             " - " + log.getContent());
        }
        System.out.println();
        
        System.out.println("9. Filtering Tasks by Status...");
        List<Task> todoTasks = system.getTaskByStatus(TaskStatus.TODO);
        List<Task> inProgressTasks = system.getTaskByStatus(TaskStatus.IN_PROGRESS);
        System.out.println("✓ TODO Tasks: " + todoTasks.size());
        for (Task task : todoTasks) {
            System.out.println("  - " + task.getTitle());
        }
        System.out.println("✓ IN_PROGRESS Tasks: " + inProgressTasks.size());
        for (Task task : inProgressTasks) {
            System.out.println("  - " + task.getTitle());
        }
        System.out.println();
        
        System.out.println("10. Filtering Tasks by Assignee...");
        List<Task> user1Tasks = system.getTaskByUser(user1.getUserId());
        System.out.println("✓ Tasks assigned to " + user1.getName() + ": " + user1Tasks.size());
        for (Task task : user1Tasks) {
            System.out.println("  - " + task.getTitle() + " (Status: " + task.getStatus() + ")");
        }
        System.out.println();
        
        System.out.println("11. Searching Tasks by Keyword...");
        List<Task> searchResults = system.searchTask("Database");
        System.out.println("✓ Search results for 'Database': " + searchResults.size());
        for (Task task : searchResults) {
            System.out.println("  - " + task.getTitle());
        }
        System.out.println();
        
        System.out.println("12. Task Details Summary...");
        System.out.println("\nTask 1 Details:");
        System.out.println("  Title: " + task1.getTitle());
        System.out.println("  Description: " + task1.getDescription());
        System.out.println("  Priority: " + task1.getPriority());
        System.out.println("  Status: " + task1.getStatus());
        System.out.println("  Assignee: " + task1.getAssigneeUserId().getName());
        System.out.println("  Tags: " + task1.getTags().size());
        System.out.println("  Comments: " + task1.getComments().size());
        System.out.println("  Activity Logs: " + task1.getActivityLogs().size());
        
        System.out.println("\n========== Demo Completed Successfully! ==========");
    }
}
