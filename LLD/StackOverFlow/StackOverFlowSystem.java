import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class StackOverFlowSystem {
    private ConcurrentHashMap<String, User> users;
    private ConcurrentHashMap<String, Question> questions;
    private ConcurrentHashMap<String, Answer> answers;
    private ConcurrentHashMap<String, Comment> comments;
    private ConcurrentHashMap<String, Vote> votes;
    private Map<String, Set<String>> userVotes;
    private static StackOverFlowSystem instance = null;

    private StackOverFlowSystem() {
        this.users = new ConcurrentHashMap<>();
        this.questions = new ConcurrentHashMap<>();
        this.answers = new ConcurrentHashMap<>();
        this.comments = new ConcurrentHashMap<>();
        this.votes = new ConcurrentHashMap<>();
        this.userVotes = new ConcurrentHashMap<>();
    }

    public static synchronized StackOverFlowSystem getInstance() {
        if (instance == null) {
            instance = new StackOverFlowSystem();
        }
        return instance;
    }

    public String createuser(String name, String email) {
        if (name == null || email == null) {
            throw new Error("Please provide valid name and email");
        }
        User user = new  User(name, email);
        String userId = user.getUserId();
        users.put(userId, user);
        return "User created successfully";
    }

    public Question postQuestion(String userId, String title, String body, List<Tag> tags) {
        User author = users.get(userId);
        if (author == null) {
            throw new Error("User not found");
        }
        if (!author.canPost()) {
             throw new Error("You dont have enough reputation points to post");
        }
        Question question = new Question(author.getUserId(), title, body, tags);
        questions.put(question.getQuestionId(), question);
        author.addQuestion(question.getQuestionId());
        System.out.println("Question is posted successfully");
        return question;
    }

    public Answer postAnswer(String userId, String content, String questionId) {
        User author = users.get(userId);
        if (author == null) {
            throw new Error("User not found");
        }

        Answer answer = new Answer(author.getUserId(), content, questionId);
        answers.put(answer.getAnswerId(), answer);

        Question question = questions.get(questionId);
        question.addAnswer(answer.getAnswerId());

        System.out.println("Answer is posted successfully");
        return answer;
    }


    public synchronized void acceptAnswer(String userId, String questionId, String answerId) {
        Question question = questions.get(questionId);
        if (question.getAuthor() != userId) {
            throw new Error("Only the user who has posted the question can accept the answer");
        }
        Answer answer = answers.get(answerId);
        answer.setAccepted(true);
        question.setAcceptedAnswerId(answerId);
        User answerAuthor = users.get(answer.getAuthor());
        answerAuthor.addReputationPoints(40);
    }


    public Comment addComment(String userId, String content, CommentType commentType, String id) {
        User author = users.get(userId);
        if (author == null) {
            throw new Error("User not found");
        }
        Comment comment = new Comment(author.getUserId(), content, commentType, id);
        comments.put(comment.getCommentId(), comment);
        if (commentType == CommentType.QUESTION) {
            Question question = questions.get(id);
            if (question == null) {
                throw new Error("Question Not found");
            }
            question.addComment(id);
        } else {
            Answer answer = answers.get(id);
            if (answer == null) {
                throw new Error("Answer Not found");
            }
            answer.addComment(id);
        }
        author.addReputationPoints(30);
        System.out.println("Comment is posted successfully");
        return comment;
    }


    public Vote vote(String userId, VoteType voteType, String postId, boolean isQuestion) {
        User author = users.get(userId);
        if (author == null) {
            throw new Error("User not found");
        }

        // Check for duplicate voting
        String voteKey = postId + "_" + isQuestion;
        Set<String> userVotedPosts = userVotes.get(userId);
        if (userVotedPosts != null && userVotedPosts.contains(voteKey)) {
            throw new Error("User has already voted on this post");
        }

        Vote vote = new Vote(author.getUserId(), voteType, postId, isQuestion);
        votes.put(vote.getVoteId(), vote);
        
        // Track user vote
        userVotes.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(voteKey);
        if (isQuestion) {
            Question question = questions.get(postId);
            if (question == null) {
                throw new Error("Question Not found");
            }
            question.addVote(vote.getVoteId());
        } else {
            Answer answer = answers.get(postId);
            if (answer == null) {
                throw new Error("Answer Not found");
            }
            answer.addVote(vote.getVoteId());
        }
        author.addReputationPoints(10);
        System.out.println("Vote is added successfully");
        return vote;
    }

    // Optional: Allow users to change their vote
    public Vote changeVote(String userId, VoteType newVoteType, String postId, boolean isQuestion) {
        User author = users.get(userId);
        if (author == null) {
            throw new Error("User not found");
        }

        String voteKey = postId + "_" + isQuestion;
        Set<String> userVotedPosts = userVotes.get(userId);
        if (userVotedPosts == null || !userVotedPosts.contains(voteKey)) {
            throw new Error("User has not voted on this post yet");
        }

        // Find and remove existing vote
        Vote existingVote = votes.values().stream()
            .filter(v -> v.getUser().equals(userId) && 
                        v.getPostId().equals(postId) && 
                        v.isQuestion() == isQuestion)
            .findFirst()
            .orElse(null);
        
        if (existingVote != null) {
            votes.remove(existingVote.getVoteId());
            // Remove from post's vote list
            if (isQuestion) {
                Question question = questions.get(postId);
                if (question != null) {
                    question.getVotes().remove(existingVote.getVoteId());
                }
            } else {
                Answer answer = answers.get(postId);
                if (answer != null) {
                    answer.getVotes().remove(existingVote.getVoteId());
                }
            }
        }

        // Create new vote
        Vote newVote = new Vote(author.getUserId(), newVoteType, postId, isQuestion);
        votes.put(newVote.getVoteId(), newVote);
        
        // Add to post's vote list
        if (isQuestion) {
            Question question = questions.get(postId);
            if (question != null) {
                question.addVote(newVote.getVoteId());
            }
        } else {
            Answer answer = answers.get(postId);
            if (answer != null) {
                answer.addVote(newVote.getVoteId());
            }
        }

        return newVote;
    }




    public ConcurrentHashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(ConcurrentHashMap<String, User> users) {
        this.users = users;
    }

    public ConcurrentHashMap<String, Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ConcurrentHashMap<String, Question> questions) {
        this.questions = questions;
    }

    public ConcurrentHashMap<String, Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ConcurrentHashMap<String, Answer> answers) {
        this.answers = answers;
    }

    public ConcurrentHashMap<String, Comment> getComments() {
        return comments;
    }

    public void setComments(ConcurrentHashMap<String, Comment> comments) {
        this.comments = comments;
    }

    public ConcurrentHashMap<String, Vote> getVotes() {
        return votes;
    }

    public void setVotes(ConcurrentHashMap<String, Vote> votes) {
        this.votes = votes;
    }
}
