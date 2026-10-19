package org.v_points.domain.school

import javax.inject.Inject

class GetDomainUseCase @Inject constructor(
    private val repository: SchoolRepository
) {
    operator fun invoke(): String = repository.getDomain()
}