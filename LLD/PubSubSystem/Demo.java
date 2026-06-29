public class Demo {
    public static void main(String[] args) {
        PubSubSystemService pubSubService = PubSubSystemService.getInstance();

        String topicId1 = pubSubService.createTopic("Sports");
        String topicId2 = pubSubService.createTopic("Technology");

        String subscriber1 = pubSubService.createSubscriber("alert");
        String subscriber2 = pubSubService.createSubscriber("alert");

        pubSubService.subscribe(topicId1, subscriber1);
        pubSubService.subscribe(topicId1, subscriber2);
        pubSubService.subscribe(topicId2, subscriber1);

        System.out.println("=== Publishing to Sports Topic ===");
        pubSubService.publish(topicId1, "India wins the cricket match!");
        pubSubService.publish(topicId1, "Virat Kohli scores a century!");

        System.out.println("\n=== Publishing to Technology Topic ===");
        pubSubService.publish(topicId2, "New AI model released!");
        pubSubService.publish(topicId2, "Java 21 is now available!");

        System.out.println("\n=== Unsubscribing subscriber1 from Sports ===");
        pubSubService.unsubscribe(topicId1, subscriber1);

        System.out.println("\n=== Publishing to Sports Topic after unsubscribe ===");
        pubSubService.publish(topicId1, "India loses the next match!");

        System.out.println("\n=== Testing failure isolation ===");
        String subscriber3 = pubSubService.createSubscriber("alert");
        pubSubService.subscribe(topicId1, subscriber3);
        pubSubService.publish(topicId1, "Final match result!");
    }
}
