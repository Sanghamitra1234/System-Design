import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {
    private String userId;
    private String name;
    private String emailid;
    private int reputationPoints;
    private List<String> questionIds;

    public User() {}

    public User(String name, String emailid) {
        this.userId = generateUserId();
        this.name = name;
        this.emailid = emailid;
        this.reputationPoints = 0;
        this.questionIds = new ArrayList<>();
    }

    private String generateUserId() {
        return "USER" + UUID.randomUUID().toString();
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

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public int getReputationPoints() {
        return reputationPoints;
    }

    public void setReputationPoints(int reputationPoints) {
        this.reputationPoints = reputationPoints;
    }

    public List<String> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<String> questionIds) {
        this.questionIds = questionIds;
    }

    public boolean canPost() {
        if (this.reputationPoints > 100) { 
            return true;
        }
        return false;
    }

    public void addQuestion(String questionId) {
        this.questionIds.add(questionId);
    }

    public void addReputationPoints(int number) {
        this.reputationPoints += number;
    }
}
