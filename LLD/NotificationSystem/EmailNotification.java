public class EmailNotification implements NotificationChannelStrategy {
    @Override
    public boolean sendNotification(Notification notification) {
        String email = notification.getRecipient().getEmail();
        
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        System.out.println("Sending email to: " + email);
        System.out.println("Subject: " + notification.getSubject());
        System.out.println("Message: " + notification.getMessage());
        
        return true;
    }
}
