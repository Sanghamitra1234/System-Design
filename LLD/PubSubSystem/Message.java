import java.util.UUID;

public class Message {
    private final String messageId;
    private final String topicId;
    private final String content;
    private final long timestamp;

    public Message(String topicId, String content) {
        this.messageId = UUID.randomUUID().toString();
        this.topicId = topicId;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
    }

    public String getMessageId() {
        return messageId;
    }

    public String getTopicId() {
        return topicId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", topicId='" + topicId + '\'' +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
