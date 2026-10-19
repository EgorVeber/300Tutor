import com.v_points.app.domain.solution.models.test_model.QuestionModel

data class TestModel(
    val description: String,
    val name: String,
    val questionList: List<QuestionModel>,
)