package org.threehundredtutor.data.solution

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import javax.inject.Inject

class SolutionRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service: SolutionService =
        serviceGeneratorProvider.getService(SolutionService::class)

    suspend fun getSolution(solutionId: String) = service.getSolution(solutionId)
}