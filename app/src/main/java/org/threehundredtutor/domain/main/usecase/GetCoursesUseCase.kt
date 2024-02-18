package org.threehundredtutor.domain.main.usecase

import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.main.models.GroupWithCourseProgressModel
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): List<GroupWithCourseProgressModel> =
        mainRepository.getCourses().list
}
