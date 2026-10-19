package org.v_points.data.common

import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.domain.common.ConfigModel
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