import java.util.*;

public class Topic {
    private String topicId;
    private String topicName;
    private List<Subscriber> subscribers;
    
    public Topic(String topicName) {
        this.topicId = UUID.randomUUID().toString();
        this.topicName = topicName;
        this.subscribers = new ArrayList<>();
    }
    
    public String getTopicId() {
        return topicId;
    }
    public String getTopicName() {
        return topicName;
    }
    
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }
    
    public void notifySubscribers(Message message) {
        for (Subscriber subscriber : subscribers) {
            try {
                subscriber.notify(message);
            } catch (Exception e) {
                System.out.println("Error notifying subscriber: " + e.getMessage());
            }
        }
    }
}