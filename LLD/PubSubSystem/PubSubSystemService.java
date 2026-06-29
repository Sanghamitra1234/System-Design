import java.util.*;
import java.util.concurrent.*;
public class PubSubSystemService {
    private static PubSubSystemService instance;
    private Map<String, Topic> topics;
    private Map<String, Subscriber> subscribers;
    
    private PubSubSystemService() {
        topics = new ConcurrentHashMap<>();
        subscribers = new ConcurrentHashMap<>();
    }
    
    public synchronized static PubSubSystemService getInstance() {
        if (instance == null) {
            instance = new PubSubSystemService();
        }
        return instance;
    }

    public String createTopic(String topicName) {
        Topic topic = new Topic(topicName);
        topics.put(topic.getTopicId(), topic);
        return topic.getTopicId();
    }

    public String createSubscriber(String subscriberType) {
        if (subscriberType.equals("alert")) {
            AlertMessageSubscriber subscriber = new AlertMessageSubscriber();
            subscribers.put(subscriber.getSubscriberId(), subscriber);
            return subscriber.getSubscriberId();
        }
        return null;
    }

    public void subscribe(String topicId, String subscriberId) {
        if (topicId == null || subscriberId == null) {
            throw new IllegalArgumentException("Topic ID and subscriber ID cannot be null");
        }
        if (!topics.containsKey(topicId)) {
            throw new IllegalArgumentException("Topic not found");
        }
        if (!subscribers.containsKey(subscriberId)) {
            throw new IllegalArgumentException("Subscriber not found");
        }
        Topic topic = topics.get(topicId);
        Subscriber subscriber = subscribers.get(subscriberId);
        topic.addSubscriber(subscriber);
    }

    public void unsubscribe(String topicId, String subscriberId) {
        if (topicId == null || subscriberId == null) {
            throw new IllegalArgumentException("Topic ID and subscriber ID cannot be null");
        }
        if (!topics.containsKey(topicId)) {
            throw new IllegalArgumentException("Topic not found");
        }
        if (!subscribers.containsKey(subscriberId)) {
            throw new IllegalArgumentException("Subscriber not found");
        }
        Topic topic = topics.get(topicId);
        Subscriber subscriber = subscribers.get(subscriberId);
        topic.removeSubscriber(subscriber);
    }


    public synchronized void publish(String topicId, String content) {
        if (topicId == null || content == null) {
            throw new IllegalArgumentException("Topic ID and content cannot be null");
        }
        if (!topics.containsKey(topicId)) {
            throw new IllegalArgumentException("Topic not found");
        }
        Topic topic = topics.get(topicId);
        Message message = new Message(topicId, content);
        topic.notifySubscribers(message);
    }

}
