import java.util.UUID;

public class AlertMessageSubscriber implements Subscriber {
    private String subscriberId;

    public AlertMessageSubscriber() {
        this.subscriberId = UUID.randomUUID().toString();
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    @Override
    public void notify(Message message) {
        System.out.println("AlertMessageSubscriber: " + message);
    }
}