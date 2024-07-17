package org.threehundredtutor.data.common.data_source

import android.util.Log
import javax.inject.Inject

class DomainLocalDataSource @Inject constructor(
    private val publicDataSource: PublicDataSource
) {
    init {
        Log.d("LocalDataSource", "DomainLocalDataSource" + this.toString())
    }

    fun setDomain(domain: String) {
        publicDataSource.setDomain(domain)
    }

    fun getDomain(): String {
        return publicDataSource.getDomain()
    }
}