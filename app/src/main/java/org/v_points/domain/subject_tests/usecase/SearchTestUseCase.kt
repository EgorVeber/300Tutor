package org.v_points.domain.subject_tests.usecase

import org.v_points.domain.subject_tests.SubjectTestsRepository
import org.v_points.domain.subject_tests.models.SearchTestModel
import javax.inject.Inject

class SearchTestUseCase @Inject constructor(
    private val subjectTestsRepository: SubjectTestsRepository
) {
    suspend operator fun invoke(subjectId: String): SearchTestModel =
        subjectTestsRepository.searchTests(subjectId)
}
