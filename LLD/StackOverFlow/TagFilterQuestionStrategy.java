import java.util.List;

public class TagFilterQuestionStrategy implements FilterQuestionStrategy {

    private List<Tag> tags;
    
    public TagFilterQuestionStrategy(List<Tag> tags) {
        this.tags = tags;
    }
    
    @Override
    public List<Question> filterQuestions(List<Question> questions) {
        // Implementation for filtering questions by tags
        return null;
    }
}
