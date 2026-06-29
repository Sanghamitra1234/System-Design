import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Answer {
    private String answerId;
    private String Author;
    private String questionId;
    private String content;
    private List<String> votes;
    private boolean isAccepted;
    private List<String> commentIds;

    public Answer() {}

    public Answer(String Author, String content, String questionId) {
        this.answerId = generateAnswerId();
        this.Author = Author;
        this.content = content;
        this.votes = new ArrayList<>();
        this.isAccepted = false;
        this.questionId = questionId;
        this.commentIds = new ArrayList<>();
    }

    private String generateAnswerId() {
        return "ANS" + UUID.randomUUID().toString();
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  List<String> getVotes() {
        return votes;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public List<String> getCommentIds() {
        return commentIds;
    }

    public void setCommentIds(List<String> commentIds) {
        this.commentIds = commentIds;
    }

    public void addComment(String commentId) {
        this.commentIds.add(commentId);
    }

    public void addVote(String voteId) {
        this.votes.add(voteId);
    }
}
