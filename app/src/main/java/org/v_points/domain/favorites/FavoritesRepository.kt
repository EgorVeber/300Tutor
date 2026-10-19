package org.v_points.domain.favorites

import org.v_points.domain.solution.models.test_model.QuestionModel

interface FavoritesRepository {
    suspend fun getFavoritesQuestion(studentId: String): List<QuestionModel>
}
