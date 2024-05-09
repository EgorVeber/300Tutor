package org.threehundredtutor.data.main

import org.threehundredtutor.data.main.mappers.toEnterGroupModel
import org.threehundredtutor.data.main.mappers.toExtraButtonInfoModel
import org.threehundredtutor.data.main.mappers.toGroupWithCourseModel
import org.threehundredtutor.data.main.mappers.toSubjectModel
import org.threehundredtutor.data.main.request.GroupWithCourseRequest
import org.threehundredtutor.domain.main.EnterGroupModel
import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.domain.main.models.GroupWithCourseModel
import org.threehundredtutor.domain.subject_tests.models.SubjectModel
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
                studentId = studentId
            )
        ).toGroupWithCourseModel()

    override suspend fun enterGroup(key: String): EnterGroupModel {
        return mainRemoteDataSource.enterGroup(key).toEnterGroupModel()
    }

    override suspend fun getExtraButtons(): List<ExtraButtonInfoModel> {
        return mainRemoteDataSource.getExtraButtons().extraButtonInfoResponses?.map {
            it.toExtraButtonInfoModel()
        }.orEmpty()
    }
}
