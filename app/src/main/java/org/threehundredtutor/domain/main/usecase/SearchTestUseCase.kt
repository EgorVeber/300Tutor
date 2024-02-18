package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.SearchTestModel
import javax.inject.Inject

class SearchTestUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(subjectId: String): SearchTestModel =
        mainRepository.searchTests(subjectId)
}
