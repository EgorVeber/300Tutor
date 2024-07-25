package org.threehundredtutor.data.solution

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class SolutionLocalDataSource {

    private var initAnswers = mapOf<String, String>()
    private var listCheckedQuestion = mutableListOf<String>()

    private var solutionAnswersFlow: MutableStateFlow<MutableMap<String, String>> =
        MutableStateFlow(mutableMapOf())

    fun saveAnswers(answers: Map<String, String>) {
        initAnswers = answers
        solutionAnswersFlow.update { answers.toMutableMap() }
    }

    fun saveAnswer(answer: Pair<String, String>) {
        solutionAnswersFlow.update { answers -> (answers + answer).toMutableMap() }
    }

    fun saveCheckAnswer(answer: Pair<String, String>) {
        listCheckedQuestion.add(answer.first)
        solutionAnswersFlow.update { answers -> (answers + answer).toMutableMap() }
        initAnswers + answer
    }

    fun getSolutionAnswersFlow(): Flow<Map<String, String>> = solutionAnswersFlow

    fun getAnswers(): Map<String, String> = solutionAnswersFlow.value
    fun getInitAnswers(): Map<String, String> = initAnswers

    fun getListCheckedQuestion(): List<String> = listCheckedQuestion
}