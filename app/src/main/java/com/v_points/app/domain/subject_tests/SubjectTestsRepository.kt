package com.v_points.app.domain.subject_tests

import com.v_points.app.domain.subject_tests.models.SearchTestModel

interface SubjectTestsRepository {
    suspend fun searchTests(subjectId: String): SearchTestModel
}
