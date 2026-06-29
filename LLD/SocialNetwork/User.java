package SocialNetwork;

import java.util.*;

public class User {
    private String userId;
    private String name;
    private String email;
    private List<String> friendIds;
    private List<String> postIds;
    private long createdAt;

    public User(String name, String email) {
        this.userId = generateUserId();
        this.name = name;
        this.email = email;
        this.friendIds = new ArrayList<>();
        this.postIds = new ArrayList<>();
        this.createdAt = System.currentTimeMillis();
    }

    private String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public void addFriend(String friendId) {
        if (!friendIds.contains(friendId)) {
            friendIds.add(friendId);
        }
    }

    public void removeFriend(String friendId) {
        friendIds.remove(friendId);
    }

    public void addPost(String postId) {
        postIds.add(postId);
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getFriendIds() {
        return new ArrayList<>(friendIds);
    }

    public List<String> getPostIds() {
        return new ArrayList<>(postIds);
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
