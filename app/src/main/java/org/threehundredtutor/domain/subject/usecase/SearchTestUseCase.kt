package org.threehundredtutor.domain.subject.usecase

import org.threehundredtutor.domain.subject.SubjectRepository
import org.threehundredtutor.domain.subject.models.SearchTestModel
import javax.inject.Inject

class SearchTestUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {
    suspend operator fun invoke(subjectId: String): SearchTestModel =
        subjectRepository.searchTests(subjectId)
}
