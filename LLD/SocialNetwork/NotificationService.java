package SocialNetwork;

public class NotificationService implements PostEventListener {
    
    @Override
    public void onPostLiked(Post post, String user) {
        System.out.println("Notification: " + user + " liked your post: " + post.getTitle());
    }
    
    @Override
    public void onPostCommented(Post post, String user, Comment comment) {
        System.out.println("Notification: " + user + " commented on your post: " + post.getTitle() + " - " + comment.getContent());
    }
}
