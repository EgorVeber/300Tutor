package com.v_points.app.domain.favorites

import com.v_points.app.domain.solution.models.test_model.QuestionModel

interface FavoritesRepository {
    suspend fun getFavoritesQuestion(studentId: String): List<QuestionModel>
}
