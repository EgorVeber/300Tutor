package org.threehundredtutor.data.subject

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.subject.request.SearchTestsRequest
import org.threehundredtutor.data.subject.response.SearchTestResponse
import org.threehundredtutor.data.subject.response.SubjectResponse
import javax.inject.Inject

class SubjectRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(SubjectService::class) }

    suspend fun getSubjects(): List<SubjectResponse> = service().getSubjects()

    suspend fun searchTests(searchTestsRequest: SearchTestsRequest): SearchTestResponse =
        service().searchTests(searchTestsRequest)
}
