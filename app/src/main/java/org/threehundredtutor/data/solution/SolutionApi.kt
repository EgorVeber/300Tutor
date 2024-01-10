package org.threehundredtutor.data.solution

object SolutionApi {
    const val TUTOR_TEST_SOLUTION_QUERY_BY_ID = "tutor/test-solution/query/Get/By/Id/{id}"
    const val TUTOR_TEST_SOLUTION_CHECK_ANSWER = "tutor/test-solution/check-answer"
    const val TUTOR_TEST_SOLUTION_FINISH = "tutor/test-solution/finish"
    const val TUTOR_TEST_SOLUTION_START_BY_TEST_ID = "tutor/test-solution/start/By/TestId"
    const val TUTOR_TEST_SOLUTION_RESULT_POINTS = "tutor/test-solution-result/points/{solutionId}"
    const val TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_SAVE =
        "tutor/test-solution-result/question/validation/save"
    const val TUTOR_TEST_SOLUTION_RESULT_QUESTIONS_VALIDATION_REMOVE =
        "tutor/test-solution-result/question/validation/remove"
    const val ID = "id"
    const val SOLUTION_ID = "solutionId"
}