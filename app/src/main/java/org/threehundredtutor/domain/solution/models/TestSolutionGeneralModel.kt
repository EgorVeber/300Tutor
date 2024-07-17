package org.threehundredtutor.domain.solution.models

import org.threehundredtutor.domain.solution.models.test_model.TestSolutionModel

data class TestSolutionGeneralModel(
    val solutionId: String,
    val hasCuratorValidation: Boolean,
    val canCheckSingleQuestion: Boolean,//Можно ли проверять каждый вопрос в тесте на правильность
    val studentGroupId: String,
    val studentId: String,
    val startedOnUtc: String,
    val finishedOnUtc: String,
    val isFinished: Boolean,
    val descriptionTest: String,
    val nameTest: String,
    val testSolutionModel: List<TestSolutionModel>,
)