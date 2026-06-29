package SocialNetwork;

public interface PostEventListener {
    void onPostLiked(Post post, String user);
    void onPostCommented(Post post, String user, Comment comment);
}