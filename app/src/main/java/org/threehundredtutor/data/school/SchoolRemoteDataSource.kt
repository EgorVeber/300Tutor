package org.threehundredtutor.data.school

import org.threehundredtutor.BuildConfig
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.school.model.GetSchoolResponse
import javax.inject.Inject

class SchoolRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {

    private val service =
        { serviceGeneratorProvider.getService(SchoolService::class, BuildConfig.BASE_URL) }

    suspend fun getSchool(): GetSchoolResponse = service().getSchool()
}
