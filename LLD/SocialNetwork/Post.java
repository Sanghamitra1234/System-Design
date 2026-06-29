package SocialNetwork;

import java.util.*;

public class Post extends SocialContentEntity{
    private String postId;
    private String title;
    private List<String> commentIds;
    private List<String> likeIds;
    private List<PostEventListener> listeners;
    private long createdAt;
    private long updatedAt;

    public Post(String userId, String title, String content) {
        super(content, userId);
        this.postId = generatePostId();
        this.title = title;
        this.commentIds = new ArrayList<>();
        this.likeIds = new ArrayList<>();
        this.listeners = new ArrayList<>();
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    private String generatePostId() {
        return UUID.randomUUID().toString();
    }

    public void addListener(PostEventListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PostEventListener listener) {
        listeners.remove(listener);
    }

    private void notifyLikeAdded(String userId) {
        for (PostEventListener listener : listeners) {
            listener.onPostLiked(this, userId);
        }
    }

    private void notifyCommentAdded(String userId, Comment comment) {
        for (PostEventListener listener : listeners) {
            listener.onPostCommented(this, userId, comment);
        }
    }

    public void addLike(String userId) {
        if (!likeIds.contains(userId)) {
            likeIds.add(userId);
            notifyLikeAdded(userId);
        }
    }

    public void addComment(Comment comment) {
        commentIds.add(comment.getCommentId());
        notifyCommentAdded(comment.getUserId(), comment);
    }

    public void removeLike(String userId) {
        likeIds.remove(userId);
    }

    public void removeComment(String commentId) {
        commentIds.remove(commentId);
    }

    public String getPostId() {
        return postId;
    }


    public String getTitle() {
        return title;
    }

    public List<String> getCommentIds() {
        return new ArrayList<>(commentIds);
    }

    public List<String> getLikeIds() {
        return new ArrayList<>(likeIds);
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public int getLikeCount() {
        return likeIds.size();
    }

    public int getCommentCount() {
        return commentIds.size();
    }
}
