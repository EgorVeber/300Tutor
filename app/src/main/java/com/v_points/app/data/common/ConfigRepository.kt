package com.v_points.app.data.common

import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.domain.common.ConfigModel
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