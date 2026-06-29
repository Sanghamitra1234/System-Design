package SocialNetwork;

public class Notification {
    public enum NotificationType {
        LIKE, COMMENT
    }

    private String notificationId;
    private String userId;
    private NotificationType type;
    private String relatedPostId;
    private String relatedUserId;
    private long createdAt;
    private boolean isRead;

    public Notification(String notificationId, String userId, NotificationType type,
                       String relatedPostId, String relatedUserId) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.type = type;
        this.relatedPostId = relatedPostId;
        this.relatedUserId = relatedUserId;
        this.createdAt = System.currentTimeMillis();
        this.isRead = false;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getUserId() {
        return userId;
    }

    public NotificationType getType() {
        return type;
    }

    public String getRelatedPostId() {
        return relatedPostId;
    }

    public String getRelatedUserId() {
        return relatedUserId;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return isRead;
    }
}
