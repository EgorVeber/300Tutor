package com.v_points.app.domain.subject_workspace.models

import com.v_points.app.domain.solution.models.test_model.TestQuestionType

class QuestionsTypeAndCountModel(
    val count: Int,
    val type: TestQuestionType // TODO из solution надо бы в коммон вынести
)