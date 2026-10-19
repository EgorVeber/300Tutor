package org.v_points.data.school

import org.v_points.data.common.data_source.DomainLocalDataSource
import org.v_points.data.school.model.toGetSchoolModel
import org.v_points.domain.school.GetSchoolModelItem
import org.v_points.domain.school.SchoolRepository
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