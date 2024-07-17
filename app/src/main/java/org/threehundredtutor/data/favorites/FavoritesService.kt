package org.threehundredtutor.data.favorites

import org.threehundredtutor.data.favorites.FavoritesApi.TUTOR_OPEN_API_QUESTION_LINK_SEARCH
import retrofit2.http.Body
import retrofit2.http.POST

interface FavoritesService {
    @POST(TUTOR_OPEN_API_QUESTION_LINK_SEARCH)
    suspend fun getFavoritesQuestion(@Body params: FavoritesRequest): FavoritesResponse
}
