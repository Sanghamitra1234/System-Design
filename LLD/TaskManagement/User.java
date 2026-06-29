import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String email;

    public User() {
    }

    public User( String name, String email) {
        this.userId = generateUserId();
        this.name = name;
        this.email = email;
    }

    private String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public boolean canPost() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'canPost'");
    }

    public void addQuestion(String questionId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addQuestion'");
    }

    public void addReputationPoints(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReputationPoints'");
    }
}
