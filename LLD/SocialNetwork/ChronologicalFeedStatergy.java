package SocialNetwork;

import java.util.List;
import java.util.stream.Collectors;

public class ChronologicalFeedStatergy implements NewsFeedStratergy {
    
    @Override
    public List<Post> showPosts(User user, List<Post> post) {
        List <String> friends = user.getFriendIds();

        return post.stream()
            .filter(p -> friends.contains(p.getUserId()))
            .sorted((p1, p2) -> Long.compare(p2.getUpdatedAt(), p1.getUpdatedAt()))
            .collect(Collectors.toList());
    }
}
