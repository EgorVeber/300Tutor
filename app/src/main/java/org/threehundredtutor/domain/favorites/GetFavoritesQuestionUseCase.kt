package org.threehundredtutor.domain.favorites

import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import javax.inject.Inject

class GetFavoritesQuestionUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(studentId: String): List<QuestionModel> =
        favoritesRepository.getFavoritesQuestion(studentId = studentId)
}