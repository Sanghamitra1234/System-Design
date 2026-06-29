package SocialNetwork;

public abstract class SocialContentEntity {
    private String content;
    private String userId;
    
    public SocialContentEntity(String content, String userId) {
        this.content = content;
        this.userId = userId;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

     public String getUserId() {
        return userId;
    }
}

