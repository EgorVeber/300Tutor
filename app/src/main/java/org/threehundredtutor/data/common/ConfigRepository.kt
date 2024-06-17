package org.threehundredtutor.data.common

import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.domain.common.ConfigModel
import javax.inject.Inject

class ConfigRepository @Inject constructor(
    private val configLocalDataSource: ConfigLocalDataSource,
) {
    fun getConfigModel() =
        ConfigModel(
            baseUrl = configLocalDataSource.baseUrl,
            changeDomain = configLocalDataSource.changeDomain,
        )
}