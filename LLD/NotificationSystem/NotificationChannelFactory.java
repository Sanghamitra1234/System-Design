public class NotificationChannelFactory {
    public NotificationChannelStrategy getStrategy(NotificationType notificationType) {
        switch (notificationType) {
            case EMAIL:
                return new EmailNotification();
            case SMS:
                return new SMSNotification();
            case PUSH:
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type: " + notificationType);
        }
    }
}
