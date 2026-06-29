import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    private String taskId;
    private String title;
    private String description;
    private LocalDateTime dueDate;
    private TaskPriority priority;
    private TaskStatus status;
    private User assigneeUserId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    List<Tag> tags;
    List<ActivityLog> activityLogs;
    List<Comment> comments;

    public Task() {
    }

    public Task(String title, String description, 
                LocalDateTime dueDate, TaskPriority priority, TaskStatus status, 
                User assigneeUserId, LocalDateTime createdAt, LocalDateTime updatedAt, List<Tag> tags,  
                List<ActivityLog> activityLogs, List<Comment> comments) {
        this.taskId = generateTaskId();
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status != null ? status : TaskStatus.TODO;
        this.assigneeUserId = assigneeUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags != null ? tags : new ArrayList<>();
        this.activityLogs = activityLogs != null ? activityLogs : new ArrayList<>();
        this.comments = comments != null ? comments : new ArrayList<>();
    }

    public String generateTaskId() {
        return "TASK-" + UUID.randomUUID().toString();
    }


    public List<Comment> getComments() {
        return this.comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addTags(Tag tag) {
        this.tags.add(tag);
    }

     public synchronized void addComments(User user, Comment comment) {
        addLog(user, "comments addition", "New Comments got added by " + user.getName());
        this.comments.add(comment);
    }

    public synchronized void changeStatus(User user, TaskStatus status) {
        addLog(user, "status change", "Status changed from " + this.status + " to " + status);
        this.setStatus(status);
    }

    public synchronized void assignTask(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        String previousAssignee = this.getAssigneeUserId() != null ? this.getAssigneeUserId().getName() : "Unassigned";
        addLog(user, "assign task", "Task assigned to value changed from " + previousAssignee + " to " + user.getName());
        this.setAssigneeUserId(user);
    }

    public synchronized void assignPriority(User user, TaskPriority priority) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        addLog(user, "Priority changes", "Priority value changed from " +this.getPriority() + " to " + priority);
        this.setPriority(priority);
    }

    public void addLog(User user, String eventType, String content) {
        ActivityLog activityLog = new ActivityLog(this, user.getUserId(), eventType, content, LocalDateTime.now());
        this.activityLogs.add(activityLog);
    }

    public List<ActivityLog> getActivityLogs() {
        return this.activityLogs;
    }

    public void setActivityLog(List<ActivityLog> activityLog) {
        this.activityLogs = activityLog;
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public User getAssigneeUserId() {
        return assigneeUserId;
    }

    public void setAssigneeUserId(User assigneeUserId) {
        this.assigneeUserId = assigneeUserId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", status=" + status +
                ", assigneeUserId='" + assigneeUserId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
    
    public static class Builder {
        private String title;
        private String description;
        private LocalDateTime dueDate;
        private TaskPriority priority;
        private TaskStatus status;
        private User assigneeUserId;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<Tag> tags;
        private List<ActivityLog> activityLogs;
        private List<Comment> comments;

        public Builder() {
            this.tags = new ArrayList<>();
            this.activityLogs = new ArrayList<>();
            this.comments = new ArrayList<>();
            this.status = TaskStatus.TODO;
            this.priority = TaskPriority.MEDIUM;
            this.createdAt = LocalDateTime.now();
            this.updatedAt = LocalDateTime.now();
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setDueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setPriority(TaskPriority priority) {
            this.priority = priority;
            return this;
        }

        public Builder setStatus(TaskStatus status) {
            this.status = status;
            return this;
        }

        public Builder setAssignee(User assigneeUserId) {
            this.assigneeUserId = assigneeUserId;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder addTag(Tag tag) {
            this.tags.add(tag);
            return this;
        }

        public Builder addTags(List<Tag> tags) {
            this.tags.addAll(tags);
            return this;
        }

        public Builder addComment(Comment comment) {
            this.comments.add(comment);
            return this;
        }

        public Builder addComments(List<Comment> comments) {
            this.comments.addAll(comments);
            return this;
        }

        public Task build() {
            if (title == null || title.isEmpty()) {
                throw new IllegalArgumentException("Title is required");
            }
            if (description == null || description.isEmpty()) {
                throw new IllegalArgumentException("Description is required");
            }

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
}
