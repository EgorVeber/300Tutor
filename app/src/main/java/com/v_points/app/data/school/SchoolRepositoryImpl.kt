package com.v_points.app.data.school

import com.v_points.app.data.common.data_source.DomainLocalDataSource
import com.v_points.app.data.school.model.toGetSchoolModel
import com.v_points.app.domain.school.GetSchoolModelItem
import com.v_points.app.domain.school.SchoolRepository
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