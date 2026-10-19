package org.v_points.data.main

import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.main.mappers.toCreateLinkTelegramModel
import org.v_points.data.main.mappers.toTelegramDataModel
import org.v_points.data.main.request.EnterGroupRequest
import org.v_points.data.main.request.GroupWithCourseRequest
import org.v_points.data.main.response.EnterGroupResponse
import org.v_points.data.main.response.ExtraButtonResponse
import org.v_points.data.main.response.GroupWithCourseResponse
import org.v_points.data.subject_tests.models.SubjectResponse
import org.v_points.domain.main.models.CreateLinkTelegramModel
import org.v_points.domain.main.models.TelegramDataModel
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
    suspend fun getTelegramData(): TelegramDataModel {
       return service().getTelegramData().toTelegramDataModel()
    }

    suspend fun createLinkTelegram(): CreateLinkTelegramModel {
        return service().createLinkTelegram().toCreateLinkTelegramModel()
    }

    companion object {
        const val ICON_SET_ID_DEFAULT = "Default"
    }
}