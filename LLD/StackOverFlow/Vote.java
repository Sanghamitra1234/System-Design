import java.time.LocalDateTime;
import java.util.UUID;

public class Vote {
    private String voteId;
    private String user;
    private VoteType voteType;
    private LocalDateTime timestamp;
    private String postId;
    private boolean isQuestion;

    public Vote() {}

    public Vote( String user, VoteType voteType, String postId, boolean isQuestion) {
        this.voteId = generateVoteId();
        this.user = user;
        this.voteType = voteType;
        this.timestamp = LocalDateTime.now();
        this.postId = postId;
        this.isQuestion = isQuestion;
    }

    private String generateVoteId() {
        return "VOTE" + UUID.randomUUID().toString();
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isQuestion() {
        return isQuestion;
    }

    public void setQuestion(boolean question) {
        isQuestion = question;
    }
}
