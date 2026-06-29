public class Notification {
    private String notificationId;
    private Recipient recipient;
    private NotificationType notificationType;
    private String subject;
    private String message;
    private NotificationStatus status;
    private int retryCount;

    private Notification(String notificationId, Recipient recipient, NotificationType notificationType,
                        String subject, String message, NotificationStatus status, int retryCount) {
        this.notificationId = notificationId;
        this.recipient = recipient;
        this.notificationType = notificationType;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.retryCount = retryCount;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public int getRetryCount() {
        return retryCount;
    }

    public void incrementRetryCount() {
        this.retryCount++;
    }

    public void resetRetryCount() {
        this.retryCount = 0;
    }

    public static class Builder {
        private String notificationId;
        private Recipient recipient;
        private NotificationType notificationType;
        private String subject;
        private String message;
        private NotificationStatus status;
        private int retryCount;
        
        public Builder() {
            this.status = NotificationStatus.PENDING;
            this.retryCount = 0;
        }
        
        public Builder setNotificationId(String notificationId) {
            this.notificationId = notificationId;
            return this;
        }
        
        public Builder setRecipient(Recipient recipient) {
            this.recipient = recipient;
            return this;
        }
        
        public Builder setNotificationType(NotificationType notificationType) {
            this.notificationType = notificationType;
            return this;
        }
        
        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }
        
        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }
        
        public Builder setStatus(NotificationStatus status) {
            this.status = status;
            return this;
        }
        
        public Builder setRetryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }
        
        public Notification build() {
            return new Notification(notificationId, recipient, notificationType, subject, message, status, retryCount);
        }
    }
}
