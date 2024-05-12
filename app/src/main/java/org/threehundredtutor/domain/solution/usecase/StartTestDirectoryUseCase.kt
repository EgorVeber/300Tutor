package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.directory.StartTestDirectoryParamsModel
import org.threehundredtutor.domain.solution.models.test_model.HtmlPageTestType
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import javax.inject.Inject

class StartTestDirectoryUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(
        workSpaceId: String,
        directoryId: String,
        htmlPageTestType: HtmlPageTestType
    ): TestSolutionGeneralModel {
        val filterList =
            when (htmlPageTestType) {
                HtmlPageTestType.FIRST_PATH -> TestQuestionType.values().toList()
                    .filter { it != TestQuestionType.DETAILED_ANSWER }
                    .filter { it != TestQuestionType.UNKNOWN }

                HtmlPageTestType.SECOND_PATH -> TestQuestionType.values().toList()
                    .filter { it == TestQuestionType.DETAILED_ANSWER }
                    .filter { it != TestQuestionType.UNKNOWN }

                HtmlPageTestType.FULL_TEST -> TestQuestionType.values().toList()
                    .filter { it != TestQuestionType.UNKNOWN }

                else -> {
                    listOf() // TODO бомба замедленного действия
                }
            }


        return solutionRepository.startByDirectory(
            StartTestDirectoryParamsModel(
                workSpaceId = workSpaceId,
                directoryId = directoryId,
                testQuestionTypeList = filterList
            )
        )
    }
}
