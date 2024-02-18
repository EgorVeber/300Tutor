package org.threehundredtutor.data.main

import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.data.main.mappers.toEnterGroupModel
import org.threehundredtutor.data.main.mappers.toExtraButtonInfoModel
import org.threehundredtutor.data.main.mappers.toGroupWithCourseModel
import org.threehundredtutor.data.main.mappers.toSearchTestModel
import org.threehundredtutor.data.main.mappers.toSubjectModel
import org.threehundredtutor.data.main.request.GroupWithCourseRequest
import org.threehundredtutor.data.main.request.SearchTestsRequest
import org.threehundredtutor.domain.main.EnterGroupModel
import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.domain.main.models.GroupWithCourseModel
import org.threehundredtutor.domain.main.models.SearchTestModel
import org.threehundredtutor.domain.main.models.SubjectModel
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource,
    private val accountManager: AccountManager,
) : MainRepository {
    override suspend fun getSubjects(): List<SubjectModel> =
        mainRemoteDataSource.getSubjects().map { subjectResponse ->
            subjectResponse.toSubjectModel()
        }

    override suspend fun searchTests(subjectId: String): SearchTestModel =
        mainRemoteDataSource.searchTests(
            SearchTestsRequest(
                count = null,
                isActive = true,
                isGlobal = true,
                offSet = 0,
                q = null,
                subjectIds = listOf(subjectId)
            )
        ).toSearchTestModel()

    override suspend fun getCourses(): GroupWithCourseModel =
        mainRemoteDataSource.getCourses(
            GroupWithCourseRequest(
                count = null,
                offSet = 0,
                q = null,
                studentId = accountManager.getAccountInfo().third
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
