package com.v_points.app.data.solution.models.directory

import com.v_points.app.domain.solution.models.directory.StartTestDirectoryParamsModel
import com.v_points.app.domain.solution.models.test_model.TestQuestionType.Companion.getName

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