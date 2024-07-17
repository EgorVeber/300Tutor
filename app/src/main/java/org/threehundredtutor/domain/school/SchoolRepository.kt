package org.threehundredtutor.domain.school

interface SchoolRepository {
    suspend fun getSchool(): List<GetSchoolModelItem>
    fun getDomain(): String
    fun setDomain(domain: String)
}
