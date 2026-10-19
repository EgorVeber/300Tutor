package org.v_points.data.main

import org.v_points.data.main.MainRemoteDataSource.Companion.ICON_SET_ID_DEFAULT
import org.v_points.data.main.mappers.toEnterGroupModel
import org.v_points.data.main.mappers.toExtraButtonInfoModel
import org.v_points.data.main.mappers.toGroupWithCourseModel
import org.v_points.data.main.mappers.toSubjectModel
import org.v_points.data.main.request.GroupWithCourseRequest
import org.v_points.domain.main.EnterGroupModel
import org.v_points.domain.main.MainRepository
import org.v_points.domain.main.models.CreateLinkTelegramModel
import org.v_points.domain.main.models.ExtraButtonInfoModel
import org.v_points.domain.main.models.GroupWithCourseModel
import org.v_points.domain.main.models.TelegramDataModel
import org.v_points.domain.subject_tests.models.SubjectModel
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
