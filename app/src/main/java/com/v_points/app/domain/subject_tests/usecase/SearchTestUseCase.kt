package com.v_points.app.domain.subject_tests.usecase

import com.v_points.app.domain.subject_tests.SubjectTestsRepository
import com.v_points.app.domain.subject_tests.models.SearchTestModel
import javax.inject.Inject

class SearchTestUseCase @Inject constructor(
    private val subjectTestsRepository: SubjectTestsRepository
) {
    suspend operator fun invoke(subjectId: String): SearchTestModel =
        subjectTestsRepository.searchTests(subjectId)
}
