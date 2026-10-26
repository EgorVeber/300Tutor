package com.v_points.app.data.common.data_source

import com.v_points.app.BuildConfig

class ConfigLocalDataSource {
    val baseUrl = BuildConfig.BASE_URL
    val changeDomain: Boolean = BuildConfig.CHANGE_DOMAIN
}