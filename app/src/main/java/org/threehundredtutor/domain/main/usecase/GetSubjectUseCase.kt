package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.subject_tests.models.SubjectModel
import javax.inject.Inject

class GetSubjectUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<SubjectModel> =
        mainRepository.getSubjects()
}
