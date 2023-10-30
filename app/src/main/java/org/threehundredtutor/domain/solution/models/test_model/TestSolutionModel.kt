import org.threehundredtutor.domain.solution.models.solution_models.AnswerModel
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel

data class TestSolutionModel(
    val questionModel: QuestionModel,
    val answerModel: AnswerModel
)

