package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.TestSolutionGeneralModel
import org.threehundredtutor.domain.solution.models.directory.StartTestDirectoryParamsModel
import javax.inject.Inject

class StartTestDirectoryUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(startTestDirectoryParamsModel: StartTestDirectoryParamsModel): TestSolutionGeneralModel{
        return solutionRepository.startByDirectory(startTestDirectoryParamsModel)
    }
}
