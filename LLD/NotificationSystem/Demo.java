public class Demo {
    public static void main(String[] args) throws InterruptedException {
        NotificationService notificationService = NotificationService.getInstance();
        
        Recipient recipient = new Recipient("user1", "test@example.com", "1234567890");
        
        Notification notification = new Notification.Builder()
            .setNotificationId("1")
            .setSubject("Test")
            .setMessage("Test Message")
            .setRecipient(recipient)
            .setNotificationType(NotificationType.EMAIL)
            .build();
        
        notificationService.sendNotification(notification);
        
        Thread.sleep(1000);
        System.out.println("Status: " + notificationService.getStatus("1"));
        
        notificationService.shutdown();
    }
}
