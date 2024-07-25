package org.threehundredtutor.data.solution

object SolutionApi {
    const val TUTOR_TEST_SOLUTION_QUERY_GET_BY_ID = "/api/tutor/test-solution/query/Get/By/Id/{id}"

    const val TUTOR_TEST_SOLUTION_QUERY_BY_ID_DETAILED =
        "/api/tutor/test-solution/query/Get/By/Id/{id}/detailed"

    const val TUTOR_TEST_SOLUTION_CHECK_ANSWER = "/api/tutor/test-solution/check-answer"

    const val TUTOR_TEST_SOLUTION_FINISH = "/api/tutor/test-solution/finish"

    const val TUTOR_TEST_SOLUTION_START_BY_TEST_ID = "/api/tutor/test-solution/start/By/TestId"
    const val TUTOR_TEST_SOLUTION_START_BY_DIRECTORY = "/api/tutor/test-solution/start/By/Directory"

    const val TUTOR_TEST_SOLUTION_RESULT_POINTS = "/api/tutor/test-solution-result/points/{solutionId}"


    const val TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE =
        "/api/tutor/test-solution-result/question/validation/save"

    const val TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_REMOVE =
        "/api/tutor/test-solution-result/question/validation/remove"

    const val TUTOR_QUESTION_LIKES_CHANGE = "/api/tutor/question-likes/change"

    const val TUTOR_TEST_SOLUTION_SAVE_ANSWERS_WITH_HOUT= "/api/tutor/test-solution/save-answers-without-validation"

    const val ID = "id"
    const val SOLUTION_ID = "solutionId"
}