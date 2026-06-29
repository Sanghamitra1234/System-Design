public class SMSNotification implements NotificationChannelStrategy {
    @Override
    public boolean sendNotification(Notification notification) {
        String phone = notification.getRecipient().getPhone();
        
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        
        System.out.println("Sending SMS to: " + phone);
        System.out.println("Message: " + notification.getMessage());
        
        return true;
    }
}