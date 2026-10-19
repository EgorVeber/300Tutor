package com.v_points.app.data.subject_tests

import com.v_points.app.data.main.mappers.toSearchTestModel
import com.v_points.app.data.subject_tests.models.SearchTestsRequest
import com.v_points.app.domain.subject_tests.SubjectTestsRepository
import com.v_points.app.domain.subject_tests.models.SearchTestModel
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