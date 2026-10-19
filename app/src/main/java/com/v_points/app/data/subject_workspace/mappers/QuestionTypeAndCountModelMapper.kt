package com.v_points.app.data.subject_workspace.mappers

import com.v_points.app.data.subject_workspace.model.QuestionsTypeAndCountResponse
import com.v_points.app.domain.solution.models.test_model.TestQuestionType
import com.v_points.app.domain.subject_workspace.models.QuestionsTypeAndCountModel
import com.v_points.app.ui_common.util.orDefaultNotValidValue


fun QuestionsTypeAndCountResponse.toQuestionsTypeAndCountModel(): QuestionsTypeAndCountModel =
    QuestionsTypeAndCountModel(
        count = count.orDefaultNotValidValue(),
        type = TestQuestionType.getType(type.orEmpty())
    )