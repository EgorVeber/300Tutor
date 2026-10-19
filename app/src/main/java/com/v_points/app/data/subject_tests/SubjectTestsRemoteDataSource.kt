package com.v_points.app.data.subject_tests

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.subject_tests.models.SearchTestResponse
import com.v_points.app.data.subject_tests.models.SearchTestsRequest
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
