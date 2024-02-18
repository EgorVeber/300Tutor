package org.threehundredtutor.data.subject_detailed

import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.data.subject_detailed.models.ConfigurationMenuResponse
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
