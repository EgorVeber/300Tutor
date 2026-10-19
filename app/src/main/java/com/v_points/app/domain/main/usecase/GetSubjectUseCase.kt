package com.v_points.app.domain.main.usecase

import com.v_points.app.domain.main.MainRepository
import com.v_points.app.domain.subject_tests.models.SubjectModel
import javax.inject.Inject

class GetSubjectUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<SubjectModel> = mainRepository.getSubjects()
}
