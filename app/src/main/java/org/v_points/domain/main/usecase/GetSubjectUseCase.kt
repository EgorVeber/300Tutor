package org.v_points.domain.main.usecase

import org.v_points.domain.main.MainRepository
import org.v_points.domain.subject_tests.models.SubjectModel
import javax.inject.Inject

class GetSubjectUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<SubjectModel> = mainRepository.getSubjects()
}
