public class Like {
    private String likeId;
    private String userId;
    private String postId;
    private long createdAt;

    public Like(String likeId, String userId, String postId) {
        this.likeId = likeId;
        this.userId = userId;
        this.postId = postId;
        this.createdAt = System.currentTimeMillis();
    }

    public String getLikeId() {
        return likeId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
