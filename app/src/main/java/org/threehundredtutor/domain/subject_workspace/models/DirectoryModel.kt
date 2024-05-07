package org.threehundredtutor.domain.subject_workspace.models

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.ui_common.EMPTY_STRING

class DirectoryModel(
    val directoryId: String,
    val directoryName: String,
    val path: String,
    val htmlPageModel: HtmlPageModel,
    val childDirectoriesList: List<DirectoryModel>,
    val questionsTypeAndCountList: List<QuestionsTypeAndCountModel>
) {
    fun hasFirstPath(): Boolean = questionsTypeAndCountList.any {
        it.type != TestQuestionType.DETAILED_ANSWER && it.count > 0
    }

    fun hasSecondPath() = questionsTypeAndCountList.any {
        it.type == TestQuestionType.DETAILED_ANSWER && it.count > 0
    }

    fun hasFullTest(): Boolean = hasSecondPath() && hasFirstPath()

    fun getFirstPathCount(): Int =
        questionsTypeAndCountList.filter {
            it.type != TestQuestionType.DETAILED_ANSWER
        }.sumOf { it.count }

    fun getSecondPathCount(): Int =
        questionsTypeAndCountList.filter {
            it.type == TestQuestionType.DETAILED_ANSWER
        }.sumOf { it.count }

    fun getFullTestCount(): Int = questionsTypeAndCountList.sumOf { it.count }

    fun hasTest(): Boolean = hasSecondPath() || hasFirstPath()
}