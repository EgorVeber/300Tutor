package org.threehundredtutor.domain.solution.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import org.threehundredtutor.domain.solution.SolutionRepository
import javax.inject.Inject

class GetSolutionAnswersFlowUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(): Flow<Pair<String, String>> =
        solutionRepository.getSolutionAnswersFlow().filter { it.isNotEmpty() }.map { answersMap ->
            answersMap.values.count { answer ->
                answer.isNotEmpty()
            }.toString() to answersMap.values.size.toString()
        }
}