package com.v_points.app.data.favorites

import com.v_points.app.data.solution.mappers.toQuestionModel
import com.v_points.app.domain.favorites.FavoritesRepository
import com.v_points.app.domain.solution.models.test_model.QuestionModel
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val favoritesRemoteDataSource: FavoritesRemoteDataSource,
) : FavoritesRepository {

    override suspend fun getFavoritesQuestion(studentId: String): List<QuestionModel> {
        return favoritesRemoteDataSource.getFavoritesQuestion(
            FavoritesRequest(
                count = 100,
                offSet = 0,
                studentId = studentId
            )
        ).list.orEmpty().mapIndexed { index, questionResponse ->
            questionResponse.toQuestionModel(index)
        }
    }
}