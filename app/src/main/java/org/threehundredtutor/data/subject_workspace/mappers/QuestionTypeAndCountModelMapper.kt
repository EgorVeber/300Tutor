package org.threehundredtutor.data.subject_workspace.mappers

import org.threehundredtutor.data.subject_workspace.model.QuestionsTypeAndCountResponse
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.domain.subject_workspace.models.QuestionsTypeAndCountModel
import org.threehundredtutor.ui_common.util.orDefaultNotValidValue


fun QuestionsTypeAndCountResponse.toQuestionsTypeAndCountModel(): QuestionsTypeAndCountModel =
    QuestionsTypeAndCountModel(
        count = count.orDefaultNotValidValue(),
        type = TestQuestionType.getType(type.orEmpty())
    )