package org.v_points.domain.main.usecase

import org.v_points.domain.main.MainRepository
import org.v_points.domain.main.models.GroupWithCourseProgressModel
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(studentId: String): List<GroupWithCourseProgressModel> =
        mainRepository.getCourses(studentId).list
}
