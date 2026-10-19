package org.v_points.data.school

import org.v_points.BuildConfig
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.school.model.GetSchoolResponse
import javax.inject.Inject

class SchoolRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service =
        { serviceGeneratorProvider.getService(SchoolService::class, BuildConfig.BASE_URL) }

    suspend fun getSchool(): GetSchoolResponse = service().getSchool()
}
