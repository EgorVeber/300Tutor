package com.v_points.app.data.main

import com.v_points.app.data.main.MainRemoteDataSource.Companion.ICON_SET_ID_DEFAULT
import com.v_points.app.data.main.mappers.toEnterGroupModel
import com.v_points.app.data.main.mappers.toExtraButtonInfoModel
import com.v_points.app.data.main.mappers.toGroupWithCourseModel
import com.v_points.app.data.main.mappers.toSubjectModel
import com.v_points.app.data.main.request.GroupWithCourseRequest
import com.v_points.app.domain.main.EnterGroupModel
import com.v_points.app.domain.main.MainRepository
import com.v_points.app.domain.main.models.CreateLinkTelegramModel
import com.v_points.app.domain.main.models.ExtraButtonInfoModel
import com.v_points.app.domain.main.models.GroupWithCourseModel
import com.v_points.app.domain.main.models.TelegramDataModel
import com.v_points.app.domain.subject_tests.models.SubjectModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource,
) : MainRepository {
    override suspend fun getSubjects(): List<SubjectModel> =
        mainRemoteDataSource.getSubjects().map { subjectResponse ->
            subjectResponse.toSubjectModel()
        }

    override suspend fun getCourses(studentId: String): GroupWithCourseModel =
        mainRemoteDataSource.getCourses(
            GroupWithCourseRequest(
                count = null,
                offSet = 0,
                q = null,
                studentId = studentId,
                iconSetId = ICON_SET_ID_DEFAULT
            )
        ).toGroupWithCourseModel()

    override suspend fun enterGroup(key: String): EnterGroupModel {
        return mainRemoteDataSource.enterGroup(key).toEnterGroupModel()
    }

    override suspend fun getTelegramData(): TelegramDataModel {
        return mainRemoteDataSource.getTelegramData()
    }

    override suspend fun createLinkTelegram(): CreateLinkTelegramModel {
        return mainRemoteDataSource.createLinkTelegram()
    }

    override suspend fun getExtraButtons(): List<ExtraButtonInfoModel> {
        return mainRemoteDataSource.getExtraButtons().extraButtonInfoResponses?.map {
            it.toExtraButtonInfoModel()
        }.orEmpty()
    }
}
