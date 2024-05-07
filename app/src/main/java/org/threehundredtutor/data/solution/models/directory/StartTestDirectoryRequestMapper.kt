package org.threehundredtutor.data.solution.models.directory

import org.threehundredtutor.domain.solution.models.directory.StartTestDirectoryParamsModel
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType.Companion.getName

fun StartTestDirectoryParamsModel.toStartTestDirectoryRequest(): StartTestDirectoryRequest =
    StartTestDirectoryRequest(
        workSpaceId = workSpaceId,
        directoryId = directoryId,
        testQuestionTypeRequest = TestQuestionTypeRequest(types = testQuestionTypeList.map { testQuestionType ->
            testQuestionType.getName()
        }),
        useFilter = useFilter,
        canCheckSingleQuestion = canCheckSingleQuestion
    )