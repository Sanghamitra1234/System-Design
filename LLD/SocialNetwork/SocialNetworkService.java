package SocialNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class SocialNetworkService {
    private static SocialNetworkService instance;
    private ConcurrentMap<String, User> users;
    private ConcurrentMap<String, Post> post;
    private NotificationService notificationService;
    
    private SocialNetworkService() {
        this.users = new ConcurrentHashMap<>();
        this.post = new ConcurrentHashMap<>();
        this.notificationService = new NotificationService();
    
    }
    
    public synchronized static SocialNetworkService getInstance() {
        if (instance == null) {
            instance = new SocialNetworkService();
        }
        return instance;
    }

    public void createUser(String name, String email) {
        User user = new User(name, email);
        users.put(user.getUserId(), user);
    }

    public synchronized void createPost(String userId, String title, String content) {
        Post post = new Post(userId, title, content);
        this.post.put(post.getPostId(), post);
        this.users.get(userId).addPost(post.getPostId());

        post.addListener(notificationService);
    }

    public synchronized void addFriend(User userId1, User userId2) {
        User user1 = this.users.get(userId1.getUserId());
        User user2 = this.users.get(userId2.getUserId());

        user1.addFriend(userId2.getUserId());   
        user2.addFriend(userId1.getUserId());
    }


    public synchronized void likePost(String userId, String postId) {
        Post post = this.post.get(postId);
        post.addLike(userId);
    }
    
    public synchronized void commentPost(String userId, String postId, String content) {
        Comment comment = new Comment(userId, postId, content);
        Post post = this.post.get(postId);
        post.addComment(comment);
    }


    public List<Post> getFeed(String userId, NewsFeedStratergy strategy) {
        User user = users.get(userId);
        List<Post> allPosts = new ArrayList<>(this.post.values());
        return strategy.showPosts(user, allPosts);
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public User getUserByName(String name) {
        for (User user : users.values()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    public Post getPost(String postId) {
        return post.get(postId);
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(post.values());
    }
}
