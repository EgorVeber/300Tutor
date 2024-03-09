package org.threehundredtutor.data.main

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.main.request.EnterGroupRequest
import org.threehundredtutor.data.main.request.GroupWithCourseRequest
import org.threehundredtutor.data.main.response.EnterGroupResponse
import org.threehundredtutor.data.main.response.ExtraButtonResponse
import org.threehundredtutor.data.main.response.GroupWithCourseResponse
import org.threehundredtutor.data.subject_tests.models.SubjectResponse
import javax.inject.Inject

class MainRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = { serviceGeneratorProvider.getService(MainService::class) }

    suspend fun getSubjects(): List<SubjectResponse> = service().getSubjects()

    suspend fun getCourses(groupWithCourseRequest: GroupWithCourseRequest): GroupWithCourseResponse =
        service().getCourses(groupWithCourseRequest)

    suspend fun enterGroup(key: String): EnterGroupResponse =
        service().enterGroup(EnterGroupRequest(key))

    suspend fun getExtraButtons(): ExtraButtonResponse = service().getExtraButtons()
}
