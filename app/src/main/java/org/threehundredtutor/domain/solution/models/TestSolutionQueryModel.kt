package org.threehundredtutor.domain.solution.models

import org.threehundredtutor.domain.solution.models.solution_models.SolutionModel
import org.threehundredtutor.domain.solution.models.test_model.TestModel

data class TestSolutionQueryModel(
    val solutionId: String,
    val hasCuratorValidation: Boolean,
    val canCheckSingleQuestion: Boolean,
    val studentGroupId: String,
    val studentId: String,
    val startedOnUtc: String,
    val finishedOnUtc: String,
    val isFinished: Boolean,
    val solutionModel: SolutionModel,
    val testModel: TestModel
)