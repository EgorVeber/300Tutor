package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import javax.inject.Inject

class GetAnswerByQuestionIdUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    operator fun invoke(questionId: String): String =
        solutionRepository.getAnswerByQuestionId(questionId)
}
