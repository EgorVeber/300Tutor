package org.threehundredtutor.data.main

import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
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
    private val service: () -> MainService = {
        serviceGeneratorProvider.getService(MainService::class)
    }

    suspend fun getSubjects(): List<SubjectResponse> =
        service().getSubjects(ICON_SET_ID_DEFAULT)

    suspend fun getCourses(groupWithCourseRequest: GroupWithCourseRequest): GroupWithCourseResponse =
        service().getCourses(groupWithCourseRequest)

    suspend fun enterGroup(key: String): EnterGroupResponse =
        service().enterGroup(EnterGroupRequest(key))

    suspend fun getExtraButtons(): ExtraButtonResponse = service().getExtraButtons()

    companion object {
        const val ICON_SET_ID_DEFAULT = "Default"
    }
}