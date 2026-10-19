package com.v_points.app.data.main

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.main.mappers.toCreateLinkTelegramModel
import com.v_points.app.data.main.mappers.toTelegramDataModel
import com.v_points.app.data.main.request.EnterGroupRequest
import com.v_points.app.data.main.request.GroupWithCourseRequest
import com.v_points.app.data.main.response.EnterGroupResponse
import com.v_points.app.data.main.response.ExtraButtonResponse
import com.v_points.app.data.main.response.GroupWithCourseResponse
import com.v_points.app.data.subject_tests.models.SubjectResponse
import com.v_points.app.domain.main.models.CreateLinkTelegramModel
import com.v_points.app.domain.main.models.TelegramDataModel
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