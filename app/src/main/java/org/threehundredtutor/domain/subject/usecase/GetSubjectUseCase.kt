package org.threehundredtutor.domain.subject.usecase

import org.threehundredtutor.domain.subject.models.SubjectModel
import org.threehundredtutor.domain.subject.SubjectRepository
import javax.inject.Inject

class GetSubjectUseCase @Inject constructor(
    private val subjectRepository: SubjectRepository
) {
    suspend operator fun invoke(): List<SubjectModel> =
        subjectRepository.getSubjects()
}
