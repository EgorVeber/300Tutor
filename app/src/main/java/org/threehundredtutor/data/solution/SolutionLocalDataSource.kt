package org.threehundredtutor.data.solution

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SolutionLocalDataSource {
    private var solutionAnswersFlow: MutableStateFlow<MutableMap<String, String>> =
        MutableStateFlow(mutableMapOf())

    fun saveAnswers(answers: Map<String, String>) {
        solutionAnswersFlow.update { answers.toMutableMap() }
    }

    fun saveAnswer(answer: Pair<String, String>) {
        solutionAnswersFlow.update { answers -> (answers + answer).toMutableMap() }
    }

    fun getSolutionAnswersFlow(): Flow<Map<String, String>> = solutionAnswersFlow

    fun getAnswers(): Map<String, String> = solutionAnswersFlow.value
}