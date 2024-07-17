package org.threehundredtutor.domain.favorites

import org.threehundredtutor.domain.solution.models.test_model.QuestionModel

interface FavoritesRepository {
    suspend fun getFavoritesQuestion(studentId: String): List<QuestionModel>
}
