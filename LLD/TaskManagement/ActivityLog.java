import java.time.LocalDateTime;
import java.util.UUID;

public class ActivityLog {
    private String activityId;
    private Task task;
    private String userId;
    private String eventType;
    private String content;
    private LocalDateTime timestamp;

    public ActivityLog() {
    }

    public ActivityLog(Task task, String userId, String eventType, 
                       String content, LocalDateTime timestamp) {
        this.activityId = generateActivityId();
        this.task = task;
        this.userId = userId;
        this.eventType = eventType;
        this.content = content;
        this.timestamp = timestamp;
    }

    private String generateActivityId() {
        return "activity_" + UUID.randomUUID().toString();
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task taskId) {
        this.task = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "activityId='" + activityId + '\'' +
                ", taskId='" + task + '\'' +
                ", userId='" + userId + '\'' +
                ", eventType='" + eventType + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
