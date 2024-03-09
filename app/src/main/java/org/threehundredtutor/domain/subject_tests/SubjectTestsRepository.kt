package org.threehundredtutor.domain.subject_tests

import org.threehundredtutor.domain.subject_tests.models.SearchTestModel

interface SubjectTestsRepository {
    suspend fun searchTests(subjectId: String): SearchTestModel
}
