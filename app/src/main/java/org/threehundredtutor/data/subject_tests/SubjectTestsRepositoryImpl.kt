package org.threehundredtutor.data.subject_tests

import org.threehundredtutor.data.main.mappers.toSearchTestModel
import org.threehundredtutor.data.subject_tests.models.SearchTestsRequest
import org.threehundredtutor.domain.subject_tests.SubjectTestsRepository
import org.threehundredtutor.domain.subject_tests.models.SearchTestModel
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