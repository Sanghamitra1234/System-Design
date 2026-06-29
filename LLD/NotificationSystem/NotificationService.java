import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class NotificationService {
    private ExecutorService executorService;
    private NotificationChannelFactory factory;
    private Map<String, Notification> notificationQueue;
    private static NotificationService instance;

    private NotificationService() {
        this.executorService = Executors.newFixedThreadPool(10);
        this.factory = new NotificationChannelFactory();
        this.notificationQueue = new ConcurrentHashMap<>();
    }

    public static synchronized NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    public void sendNotification(Notification notification) {
        notificationQueue.put(notification.getNotificationId(), notification);
        notification.setStatus(NotificationStatus.PENDING);

        executorService.submit(() -> {
            NotificationChannelStrategy strategy = factory.getStrategy(notification.getNotificationType());
            boolean success = strategy.sendNotification(notification);
            if (success) {
                notification.setStatus(NotificationStatus.SENT);
            } else {
                notification.setStatus(NotificationStatus.FAILED);
            }
        });
    }
    public NotificationStatus getStatus(String notificationId) {
        Notification notification = notificationQueue.get(notificationId);
        if (notification == null) {
            return null;
        }
        return notification.getStatus();
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
