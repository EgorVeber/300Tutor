package org.threehundredtutor.domain.solution.usecase

import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.domain.solution.models.BaseApiModel
import javax.inject.Inject

class ChangeLikeQuestionUseCase @Inject constructor(
    private val solutionRepository: SolutionRepository
) {
    suspend operator fun invoke(questionId: String, hasLike: Boolean): BaseApiModel =
        solutionRepository.changeLikeQuestion(questionId = questionId, hasLike = hasLike)
}
