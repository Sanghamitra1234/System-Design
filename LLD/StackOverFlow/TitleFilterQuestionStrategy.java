import java.util.List;

public class TitleFilterQuestionStrategy implements FilterQuestionStrategy {
        private String title;
    
    public TitleFilterQuestionStrategy(String title) {
        this.title = title;
    }
    
    @Override
    public List<Question> filterQuestions(List<Question> questions) {
        // Implementation for filtering questions by tags
        return null;
    }
}
