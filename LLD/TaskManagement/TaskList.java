public class TaskList {
    private String taskListId;
    private String name;
    private String description;
    private String ownerId;

    public TaskList() {
    }

    public TaskList(String taskListId, String name, String description, String ownerId) {
        this.taskListId = taskListId;
        this.name = name;
        this.description = description;
        this.ownerId = ownerId;
    }

    public String getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(String taskListId) {
        this.taskListId = taskListId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "TaskList{" +
                "taskListId='" + taskListId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ownerId='" + ownerId + '\'' +
                '}';
    }
}
