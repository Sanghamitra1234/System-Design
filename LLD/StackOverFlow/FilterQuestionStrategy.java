import java.util.List;

public interface FilterQuestionStrategy {
    List<Question> filterQuestions(List<Question> questions);
}
