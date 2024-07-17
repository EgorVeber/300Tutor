package org.threehundredtutor.data.restore

import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import javax.inject.Inject

class RestoreRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(RestoreService::class) }

    suspend fun forgotStart(restoreRequest: RestoreRequest): RestoreResponse =
        service().forgotStart(restoreRequest)
}
