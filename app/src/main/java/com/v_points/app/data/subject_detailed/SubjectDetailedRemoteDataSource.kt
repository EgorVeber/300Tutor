package com.v_points.app.data.subject_detailed

import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.subject_detailed.models.ConfigurationMenuResponse
import javax.inject.Inject

class SubjectDetailedRemoteDataSource @Inject constructor(
    serviceGeneratorProvider: ServiceGeneratorProvider
) {
    private val service = {
        serviceGeneratorProvider.getService(SubjectDetailedService::class)
    }

    suspend fun getMenuSubjectConfig(idOrAlias: String): ConfigurationMenuResponse =
        service().getMenuSubjectConfig(idOrAlias = idOrAlias)
}
