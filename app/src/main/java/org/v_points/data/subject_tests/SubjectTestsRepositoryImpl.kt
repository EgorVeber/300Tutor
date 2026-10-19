package org.v_points.data.subject_tests

import org.v_points.data.main.mappers.toSearchTestModel
import org.v_points.data.subject_tests.models.SearchTestsRequest
import org.v_points.domain.subject_tests.SubjectTestsRepository
import org.v_points.domain.subject_tests.models.SearchTestModel
import javax.inject.Inject

class SubjectTestsRepositoryImpl @Inject constructor(
    private val subjectTestsRemoteDataSource: SubjectTestsRemoteDataSource,
) : SubjectTestsRepository {

    override suspend fun searchTests(subjectId: String): SearchTestModel =
        subjectTestsRemoteDataSource.searchTests(
            SearchTestsRequest(
                count = null,
                isActive = true,
                isGlobal = true,
                offSet = 0,
                q = null,
                subjectIds = listOf(subjectId)
            )
        ).toSearchTestModel()
}