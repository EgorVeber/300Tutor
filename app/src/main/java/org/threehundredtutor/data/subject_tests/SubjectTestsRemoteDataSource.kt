package org.threehundredtutor.data.subject_tests

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.subject_tests.models.SearchTestResponse
import org.threehundredtutor.data.subject_tests.models.SearchTestsRequest
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
