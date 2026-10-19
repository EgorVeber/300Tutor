package com.v_points.app.data.starter

import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.domain.starter.StarterRepository
import javax.inject.Inject

class StarterRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : StarterRepository {
    override fun getFirstStartApp(): Boolean = publicDataSource.getFirstStartApp()
    override fun setFirstStartApp() {
        publicDataSource.setFirstStartApp()
    }
}