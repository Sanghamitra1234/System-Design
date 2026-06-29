# Builder Pattern - Complete Guide

## Overview
The Builder Pattern is a creational design pattern that provides a flexible solution to construct complex objects step-by-step.

## Key Characteristics

| Aspect | Details |
|--------|---------|
| **Type** | Creational Design Pattern |
| **Purpose** | Simplify object construction with many parameters |
| **When to Use** | Objects with 4+ optional/required parameters |
| **Benefit** | Improved readability and maintainability |

## Structure

```
┌─────────────────────────────────────────┐
│         TaskBuilder (Builder)           │
├─────────────────────────────────────────┤
│ - title: String                         │
│ - description: String                   │
│ - dueDate: LocalDateTime                │
│ - priority: TaskPriority                │
│ - status: TaskStatus                    │
│ - assigneeUserId: User                  │
│ - tags: List<Tag>                       │
│ - comments: List<Comment>               │
│ - activityLogs: List<ActivityLog>       │
├─────────────────────────────────────────┤
│ + setTitle(String): TaskBuilder         │
│ + setDescription(String): TaskBuilder   │
│ + setDueDate(LocalDateTime): TaskBuilder│
│ + setPriority(TaskPriority): TaskBuilder│
│ + setStatus(TaskStatus): TaskBuilder    │
│ + setAssignee(User): TaskBuilder        │
│ + addTag(Tag): TaskBuilder              │
│ + build(): Task                         │
└─────────────────────────────────────────┘
         │
         │ creates
         ▼
    ┌─────────┐
    │  Task   │
    └─────────┘
```

## Before vs After

### BEFORE (Without Builder)
```java
Task task = new Task(
    "Design Database",
    "Create ER diagram...",
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
```

**Problems:**
- ❌ Hard to read
- ❌ Easy to mix up parameter order
- ❌ Many parameters to remember
- ❌ Difficult to add optional fields

### AFTER (With Builder)
```java
Task task = new TaskBuilder()
    .setTitle("Design Database")
    .setDescription("Create ER diagram...")
    .setDueDate(LocalDateTime.now().plusDays(5))
    .setPriority(TaskPriority.HIGH)
    .setStatus(TaskStatus.TODO)
    .setAssignee(user1)
    .build();
```

**Benefits:**
- ✅ Crystal clear what each field is
- ✅ No parameter order confusion
- ✅ Easy to understand intent
- ✅ Simple to add new optional fields

## Implementation Details

### 1. TaskBuilder Class Structure

```java
public class TaskBuilder {
    // Step 1: Declare all fields
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private User assigneeUserId;
    private List<Tag> tags;
    private List<Comment> comments;
    private List<ActivityLog> activityLogs;

    // Step 2: Constructor with default values
    public TaskBuilder() {
        this.tags = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.activityLogs = new ArrayList<>();
        this.status = TaskStatus.TODO;        // Default status
        this.priority = TaskPriority.MEDIUM;  // Default priority
        this.createdAt = LocalDateTime.now();
    }

    // Step 3: Setter methods that return 'this'
    public TaskBuilder setTitle(String title) {
        this.title = title;
        return this;  // Return builder for chaining
    }

    public TaskBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    // ... more setters ...

    // Step 4: Build method that creates the actual object
    public Task build() {
        // Validation
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title is required");
        }
        
        // Create and return the object
        return new Task(
            title,
            description,
            dueDate,
            priority,
            status,
            assigneeUserId,
            createdAt,
            updatedAt,
            tags,
            activityLogs,
            comments
        );
    }
}
```

## Usage Examples

### Example 1: Basic Task
```java
Task task = new TaskBuilder()
    .setTitle("Design Database")
    .setDescription("Create ER diagram")
    .build();
```

### Example 2: Task with Priority and Assignee
```java
Task task = new TaskBuilder()
    .setTitle("Implement APIs")
    .setDescription("Build REST endpoints")
    .setPriority(TaskPriority.HIGH)
    .setAssignee(user1)
    .build();
```

### Example 3: Task with Tags
```java
Task task = new TaskBuilder()
    .setTitle("Setup CI/CD")
    .setDescription("Configure pipeline")
    .setPriority(TaskPriority.CRITICAL)
    .setAssignee(user1)
    .addTag(new Tag("devops", "DevOps"))
    .addTag(new Tag("infrastructure", "Infrastructure"))
    .build();
```

### Example 4: Task with Custom Dates
```java
Task task = new TaskBuilder()
    .setTitle("Code Review")
    .setDescription("Review pull requests")
    .setDueDate(LocalDateTime.now().plusDays(2))
    .setCreatedAt(LocalDateTime.now().minusDays(1))
    .setUpdatedAt(LocalDateTime.now())
    .build();
```

## Key Concepts

### 1. Method Chaining (Fluent Interface)
Each setter returns `this`, allowing you to chain calls:
```java
builder
    .setTitle("Task")
    .setDescription("Desc")
    .setPriority(TaskPriority.HIGH)
    .build();
```

### 2. Default Values
The constructor provides sensible defaults:
```java
public TaskBuilder() {
    this.status = TaskStatus.TODO;        // Default
    this.priority = TaskPriority.MEDIUM;  // Default
    this.tags = new ArrayList<>();        // Initialize empty list
}
```

### 3. Validation in build()
Validation happens when building, not during construction:
```java
public Task build() {
    if (title == null || title.isEmpty()) {
        throw new IllegalArgumentException("Title is required");
    }
    // ... create and return Task
}
```

## Advantages

| Advantage | Explanation |
|-----------|-------------|
| **Readability** | Code is self-documenting |
| **Flexibility** | Set only required fields |
| **Maintainability** | Easy to add new fields |
| **Immutability** | Can create immutable objects |
| **Validation** | Centralized validation in build() |
| **Defaults** | Sensible default values |
| **Chainable** | Fluent interface |

## Disadvantages

| Disadvantage | Explanation |
|--------------|-------------|
| **Extra Class** | Need to create builder class |
| **Boilerplate** | More code to write initially |
| **Memory** | Builder object takes memory |
| **Complexity** | Overkill for simple objects |

## When to Use Builder Pattern

✅ **Use when:**
- Object has 4+ parameters
- Many optional parameters
- Object construction is complex
- Want immutable objects
- Need fluent API

❌ **Don't use when:**
- Object has 1-2 parameters
- All parameters are required
- Simple object construction
- Performance is critical (memory overhead)

## Real-World Analogy

Think of building a house:
- **Without Builder**: Contractor needs all materials at once
- **With Builder**: You specify what you want step-by-step
  - "I want 3 bedrooms"
  - "Add a garage"
  - "Make it modern style"
  - "Build it!"

## Integration with TaskManagementSystem

Update `TaskManagementSystem.createTask()`:

```java
// Old way
public Task createTask(String title, String description, 
                      LocalDateTime dueDate, TaskPriority priority, 
                      TaskStatus status, User assignee, ...) {
    Task task = new Task(title, description, dueDate, priority, 
                        status, assignee, ...);
    tasks.put(task.getTaskId(), task);
    return task;
}

// New way (optional helper)
public Task createTaskFromBuilder(TaskBuilder builder) {
    Task task = builder.build();
    tasks.put(task.getTaskId(), task);
    return task;
}
```

## Summary

The Builder Pattern is a powerful tool for:
1. **Simplifying** complex object construction
2. **Improving** code readability
3. **Enhancing** maintainability
4. **Providing** flexible object creation

It's especially useful in your Task Management System where Task objects have many optional parameters!
