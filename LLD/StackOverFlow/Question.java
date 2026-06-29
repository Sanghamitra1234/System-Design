import java.util.ArrayList;
import java.util.List;

public class Question {
    private String questionId;
    private String Author;
    private String title;
    private String content;
    private List<Tag> tags;
    private List<String> votes;
    private String acceptedAnswerId;
    private List<String> answerIds;
    private List<String> commentIds;

    public Question() {}

    public Question( String Author, String title, String content, List<Tag> tags) {
        this.questionId = generateQuestionId();
        this.Author = Author;
        this.title = title;
        this.content = content;
        this.tags = tags == null ? new ArrayList<>() : tags;
        this.votes = new ArrayList<>();
        this.acceptedAnswerId = null;
        this.answerIds = new ArrayList<>();
        this.commentIds = new ArrayList<>();
    }

    private String generateQuestionId() {
        return "QUE" + java.util.UUID.randomUUID().toString();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<String> getVotes() {
        return votes;
    }

    public void setVotes(List<String> votes) {
        this.votes = votes;
    }

    public String getAcceptedAnswerId() {
        return acceptedAnswerId;
    }

    public void setAcceptedAnswerId(String acceptedAnswerId) {
        this.acceptedAnswerId = acceptedAnswerId;
    }

    public List<String> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<String> answerIds) {
        this.answerIds = answerIds;
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

    public void addAnswer(String answerId) {
        this.answerIds.add(answerId);
    }

}
