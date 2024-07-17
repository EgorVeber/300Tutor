package org.threehundredtutor.domain.subject_workspace.models

import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType

class QuestionsTypeAndCountModel(
    val count: Int,
    val type: TestQuestionType // TODO из solution надо бы в коммон вынести
)