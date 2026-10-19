package com.v_points.app.domain.solution.usecase

import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.domain.solution.models.BaseApiModel
import javax.inject.Inject

class ChangeLikeQuestionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(questionId: String, hasLike: Boolean): BaseApiModel =
        solutionRepository.changeLikeQuestion(questionId = questionId, hasLike = hasLike)
}
