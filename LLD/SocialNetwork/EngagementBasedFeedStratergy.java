package SocialNetwork;

import java.util.List;
import java.util.stream.Collectors;

public class EngagementBasedFeedStratergy implements NewsFeedStratergy {
    
    @Override
    public List<Post> showPosts(User user, List<Post> post) {
        List <String> friends = user.getFriendIds();

        return post.stream()
        .filter(p -> friends.contains(p.getUserId()))
        .sorted((a,b) -> Integer.compare(b.getLikeCount(), a.getLikeCount()))
        .collect(Collectors.toList());
    }
}
