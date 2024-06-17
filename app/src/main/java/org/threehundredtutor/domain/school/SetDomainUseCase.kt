package org.threehundredtutor.domain.school

import javax.inject.Inject

class SetDomainUseCase @Inject constructor(
    private val repository: SchoolRepository
) {
    operator fun invoke(domain: String) {
        repository.setDomain(domain)
    }
}