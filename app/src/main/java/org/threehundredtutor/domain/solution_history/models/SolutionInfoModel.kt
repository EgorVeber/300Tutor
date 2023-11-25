package org.threehundredtutor.domain.solution_history.models

data class SolutionInfoModel(
    val finishedOnUtc: String,
    val id: String,
    val isFinished: Boolean,
    val startedOnUtc: String,
    val testId: String,
    val testName: String
) {
    companion object {
        val EMPTY = SolutionInfoModel(
            finishedOnUtc = "",
            id = "",
            isFinished = false,
            startedOnUtc = "",
            testId = "",
            testName = ""
        )
    }
}