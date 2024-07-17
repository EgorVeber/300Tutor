package org.threehundredtutor.data.favorites

import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import javax.inject.Inject

class FavoritesRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: () -> FavoritesService = {
        serviceGeneratorProvider.getService(FavoritesService::class)
    }

    suspend fun getFavoritesQuestion(favoritesRequest: FavoritesRequest) =
        service().getFavoritesQuestion(favoritesRequest)
}