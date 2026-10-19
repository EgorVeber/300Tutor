package org.v_points.data.favorites

import org.v_points.data.solution.mappers.toQuestionModel
import org.v_points.domain.favorites.FavoritesRepository
import org.v_points.domain.solution.models.test_model.QuestionModel
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