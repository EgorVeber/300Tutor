package org.v_points.data.subject_workspace.mappers

import org.v_points.data.subject_workspace.model.QuestionsTypeAndCountResponse
import org.v_points.domain.solution.models.test_model.TestQuestionType
import org.v_points.domain.subject_workspace.models.QuestionsTypeAndCountModel
import org.v_points.ui_common.util.orDefaultNotValidValue


fun QuestionsTypeAndCountResponse.toQuestionsTypeAndCountModel(): QuestionsTypeAndCountModel =
    QuestionsTypeAndCountModel(
        count = count.orDefaultNotValidValue(),
        type = TestQuestionType.getType(type.orEmpty())
    )