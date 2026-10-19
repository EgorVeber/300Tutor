package com.v_points.app.domain.main.usecase

import com.v_points.app.domain.main.MainRepository
import com.v_points.app.domain.main.models.GroupWithCourseProgressModel
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(studentId: String): List<GroupWithCourseProgressModel> =
        mainRepository.getCourses(studentId).list
}
