import java.util.ArrayList;
import java.util.List;

public class Comment {
    private String commentId;
    private String Author;
    private String content;
    private List<Vote> votes;
    private CommentType commentType;
    private String parentId; // ID of the question or answer this comment belongs to

    public Comment() {}

    public Comment(String Author, String content, CommentType commentType, String parentId) {
        this.commentId = generateCommentId();
        this.Author = Author;
        this.content = content;
        this.votes = new ArrayList<>();
        this.commentType = commentType;
        this.parentId = parentId;
    }

    public String generateCommentId() {
        return "COM" + java.util.UUID.randomUUID().toString();
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public CommentType getCommentType() {
        return commentType;
    }

    public void setCommentType(CommentType commentType) {
        this.commentType = commentType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
