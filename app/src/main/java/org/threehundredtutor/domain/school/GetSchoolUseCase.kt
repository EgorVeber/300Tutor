package org.threehundredtutor.domain.school

import javax.inject.Inject

class GetSchoolUseCase @Inject constructor(
    private val repository: SchoolRepository
) {
    suspend operator fun invoke(): List<GetSchoolModelItem> = repository.getSchool()
}