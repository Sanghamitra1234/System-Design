package SocialNetwork;

public class Comment extends SocialContentEntity{
    private String commentId;
    private String postId;
    private long createdAt;

    public Comment( String userId, String postId, String content) {
        super(content, userId);
        this.commentId = generateCommentId();
        this.postId = postId;
        this.createdAt = System.currentTimeMillis();
    }

    private String generateCommentId() {
        return "comment_" + System.currentTimeMillis();
    }

    public String getCommentId() {
        return commentId;
    }

    public String getPostId() {
        return postId;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
