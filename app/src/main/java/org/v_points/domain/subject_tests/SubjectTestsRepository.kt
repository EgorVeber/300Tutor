package org.v_points.domain.subject_tests

import org.v_points.domain.subject_tests.models.SearchTestModel

interface SubjectTestsRepository {
    suspend fun searchTests(subjectId: String): SearchTestModel
}
