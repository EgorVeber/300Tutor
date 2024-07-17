package org.threehundredtutor.data.school

import org.threehundredtutor.data.common.data_source.DomainLocalDataSource
import org.threehundredtutor.data.school.model.toGetSchoolModel
import org.threehundredtutor.domain.school.GetSchoolModelItem
import org.threehundredtutor.domain.school.SchoolRepository
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val schoolRemoteDataSource: SchoolRemoteDataSource,
    private val domainLocalDataSource: DomainLocalDataSource,
) : SchoolRepository {
    override suspend fun getSchool(): List<GetSchoolModelItem> {
        return schoolRemoteDataSource.getSchool().toGetSchoolModel()
    }

    override fun getDomain() = domainLocalDataSource.getDomain()
    override fun setDomain(domain: String) = domainLocalDataSource.setDomain(domain)
}