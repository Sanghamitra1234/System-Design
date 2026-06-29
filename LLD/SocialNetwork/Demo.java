package SocialNetwork;

import java.util.List;

public class Demo {
    public static void main(String[] args) {
        SocialNetworkService service = SocialNetworkService.getInstance();

        System.out.println("=== Creating Users ===");
        service.createUser("Alice", "alice@example.com");
        service.createUser("Bob", "bob@example.com");
        service.createUser("Charlie", "charlie@example.com");
        System.out.println("✓ Created 3 users\n");

        System.out.println("=== Adding Friends ===");
        User alice = service.getUserByName("Alice");
        User bob = service.getUserByName("Bob");
        User charlie = service.getUserByName("Charlie");

        service.addFriend(alice, bob);
        service.addFriend(alice, charlie);
        System.out.println("✓ Alice added Bob and Charlie as friends\n");

        System.out.println("=== Creating Posts ===");
        service.createPost(alice.getUserId(), "My First Post", "Hello everyone! This is my first post.");
        service.createPost(bob.getUserId(), "Bob's Post", "Just had a great day!");
        service.createPost(charlie.getUserId(), "Charlie's Post", "Learning LLD design patterns");
        System.out.println("✓ Created 3 posts\n");

        System.out.println("=== Liking Posts ===");
        List<Post> allPosts = service.getAllPosts();
        Post alicePost = allPosts.get(0);
        Post bobPost = allPosts.get(1);

        service.likePost(bob.getUserId(), alicePost.getPostId());
        service.likePost(charlie.getUserId(), alicePost.getPostId());
        service.likePost(alice.getUserId(), bobPost.getPostId());
        System.out.println("✓ Users liked posts\n");

        System.out.println("=== Commenting on Posts ===");
        service.commentPost(bob.getUserId(), alicePost.getPostId(), "Great post Alice!");
        service.commentPost(charlie.getUserId(), alicePost.getPostId(), "I agree!");
        System.out.println("✓ Users commented on posts\n");

        System.out.println("=== Viewing Feeds ===");
        System.out.println("\n--- Alice's Chronological Feed ---");
        NewsFeedStratergy chronoFeed = new ChronologicalFeedStatergy();
        List<Post> aliceFeed = service.getFeed(alice.getUserId(), chronoFeed);
        displayFeed(aliceFeed);

        System.out.println("\n--- Alice's Engagement-Based Feed ---");
        NewsFeedStratergy engagementFeed = new EngagementBasedFeedStratergy();
        List<Post> aliceEngagementFeed = service.getFeed(alice.getUserId(), engagementFeed);
        displayFeed(aliceEngagementFeed);

        System.out.println("\n=== Post Statistics ===");
        for (Post post : aliceFeed) {
            System.out.println("Post: " + post.getTitle());
            System.out.println("  Likes: " + post.getLikeCount());
            System.out.println("  Comments: " + post.getCommentCount());
        }
    }

    private static void displayFeed(List<Post> feed) {
        if (feed.isEmpty()) {
            System.out.println("No posts in feed");
            return;
        }
        for (int i = 0; i < feed.size(); i++) {
            Post post = feed.get(i);
            System.out.println((i + 1) + ". " + post.getTitle() + " (Likes: " + post.getLikeCount() + ", Comments: " + post.getCommentCount() + ")");
        }
    }
}
