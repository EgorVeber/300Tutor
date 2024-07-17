package org.threehundredtutor.data.common.data_source

import android.util.Log
import org.threehundredtutor.BuildConfig

class ConfigLocalDataSource {
    init {
        Log.d("LocalDataSource", "ConfigLocalDataSource" + this.toString())
    }

    val baseUrl = BuildConfig.BASE_URL
    val changeDomain: Boolean = BuildConfig.CHANGE_DOMAIN
}