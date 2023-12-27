package org.threehundredtutor.data.solution

import org.threehundredtutor.domain.solution.models.test_model.TestSolutionModel

class SolutionLocalDataSource {
    private var solutionAnswers: MutableMap<String, String> = mutableMapOf()

    fun saveAnswers(testSolutionModeList: List<TestSolutionModel>) {
        testSolutionModeList.map { testSolutionModel ->
            solutionAnswers[testSolutionModel.questionModel.questionId] =
                testSolutionModel.answerModel.answerOrAnswers
        }
    }

    fun saveAnswer(questionId: String, answerOrAnswers: String) {
        solutionAnswers[questionId] = answerOrAnswers
    }

    fun getAnswers(): Map<String, String> = solutionAnswers.toMap()

    fun isAllQuestionsHaveAnswers() =
        solutionAnswers.size == solutionAnswers.filter { it.value.isNotEmpty() }.size
}

