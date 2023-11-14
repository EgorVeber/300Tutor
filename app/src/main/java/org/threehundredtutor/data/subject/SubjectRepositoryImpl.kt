package org.threehundredtutor.data.subject

import org.threehundredtutor.data.subject.mappers.toSearchTestModel
import org.threehundredtutor.data.subject.mappers.toSubjectModel
import org.threehundredtutor.data.subject.request.SearchTestsRequest
import org.threehundredtutor.domain.subject.models.SearchTestModel
import org.threehundredtutor.domain.subject.models.SubjectModel
import org.threehundredtutor.domain.subject.SubjectRepository
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(
    private val subjectRemoteDataSource: SubjectRemoteDataSource,
) : SubjectRepository {
    override suspend fun getSubjects(): List<SubjectModel> =
        subjectRemoteDataSource.getSubjects().map { subjectResponse ->
            subjectResponse.toSubjectModel()
        }

    override suspend fun searchTests(subjectId: String): SearchTestModel =
        subjectRemoteDataSource.searchTests(
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
