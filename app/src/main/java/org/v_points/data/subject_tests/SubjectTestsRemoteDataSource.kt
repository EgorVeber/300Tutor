package org.v_points.data.subject_tests

import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.subject_tests.models.SearchTestResponse
import org.v_points.data.subject_tests.models.SearchTestsRequest
import javax.inject.Inject

class SubjectTestsRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = {
        serviceGeneratorProvider.getService(SubjectTestsService::class)
    }

    suspend fun searchTests(searchTestsRequest: SearchTestsRequest): SearchTestResponse =
        service().searchTests(searchTestsRequest)
}
