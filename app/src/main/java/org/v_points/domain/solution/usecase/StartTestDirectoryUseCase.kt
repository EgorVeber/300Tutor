package org.v_points.domain.solution.usecase

import org.v_points.domain.solution.SolutionRepository
import org.v_points.domain.solution.models.TestSolutionGeneralModel
import org.v_points.domain.solution.models.directory.StartTestDirectoryParamsModel
import org.v_points.domain.solution.models.test_model.HtmlPageTestType
import org.v_points.domain.solution.models.test_model.TestQuestionType
import javax.inject.Inject

class StartTestDirectoryUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        workSpaceId: String,
        directoryId: String,
        htmlPageTestType: HtmlPageTestType
    ): TestSolutionGeneralModel {
        val testQuestionTypes = TestQuestionType.values().toList()
        val filterList =
            when (htmlPageTestType) {
                HtmlPageTestType.FIRST_PATH -> testQuestionTypes
                    .filter { testQuestionTyp -> testQuestionTyp != TestQuestionType.DETAILED_ANSWER }

                HtmlPageTestType.SECOND_PATH -> testQuestionTypes
                    .filter { testQuestionTyp -> testQuestionTyp == TestQuestionType.DETAILED_ANSWER }

                HtmlPageTestType.FULL_TEST -> testQuestionTypes

                else -> throw IllegalArgumentException()

            }.filter { testQuestionTyp -> testQuestionTyp != TestQuestionType.UNKNOWN }

        return solutionRepository.startByDirectory(
            StartTestDirectoryParamsModel(
                workSpaceId = workSpaceId,
                directoryId = directoryId,
                testQuestionTypeList = filterList
            )
        )
    }
}
