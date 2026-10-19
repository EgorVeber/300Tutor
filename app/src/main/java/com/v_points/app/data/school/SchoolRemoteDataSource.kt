package com.v_points.app.data.school

import com.v_points.app.BuildConfig
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.school.model.GetSchoolResponse
import javax.inject.Inject

class SchoolRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service =
        { serviceGeneratorProvider.getService(SchoolService::class, BuildConfig.BASE_URL) }

    suspend fun getSchool(): GetSchoolResponse = service().getSchool()
}
