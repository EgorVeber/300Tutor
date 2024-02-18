package org.threehundredtutor.data.main

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.main.request.EnterGroupRequest
import org.threehundredtutor.data.main.request.GroupWithCourseRequest
import org.threehundredtutor.data.main.request.SearchTestsRequest
import org.threehundredtutor.data.main.response.EnterGroupResponse
import org.threehundredtutor.data.main.response.ExtraButtonResponse
import org.threehundredtutor.data.main.response.GroupWithCourseResponse
import org.threehundredtutor.data.main.response.SearchTestResponse
import org.threehundredtutor.data.main.response.SubjectResponse
import javax.inject.Inject

class MainRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(MainService::class) }

    suspend fun getSubjects(): List<SubjectResponse> = service().getSubjects()

    suspend fun searchTests(searchTestsRequest: SearchTestsRequest): SearchTestResponse =
        service().searchTests(searchTestsRequest)

    suspend fun getCourses(groupWithCourseRequest: GroupWithCourseRequest): GroupWithCourseResponse =
        service().getCourses(groupWithCourseRequest)

    suspend fun enterGroup(key: String): EnterGroupResponse =
        service().enterGroup(EnterGroupRequest(key))

    suspend fun getExtraButtons(): ExtraButtonResponse = service().getExtraButtons()
}
