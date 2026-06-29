public class PushNotification implements NotificationChannelStrategy {
    @Override
    public boolean sendNotification(Notification notification) {
        System.out.println("Sending push notification");
        System.out.println("Title: " + notification.getSubject());
        System.out.println("Message: " + notification.getMessage());
        
        return true;
    }
}
