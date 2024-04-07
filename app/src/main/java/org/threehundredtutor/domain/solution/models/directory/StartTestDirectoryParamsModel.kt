package org.threehundredtutor.domain.solution.models.directory


import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType

class StartTestDirectoryParamsModel(
    val workSpaceId: String,
    val directoryId: String,
    val testQuestionTypeList: List<TestQuestionType>,
    val useFilter: Boolean = true,// TODO пока всегда true
    val canCheckSingleQuestion: Boolean = true, // TODO пока всегда true узнать откуда его брать и сделать обработку
)