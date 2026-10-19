package org.v_points.data.starter

import org.v_points.data.common.data_source.PublicDataSource
import org.v_points.domain.starter.StarterRepository
import javax.inject.Inject

class StarterRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : StarterRepository {
    override fun getFirstStartApp(): Boolean = publicDataSource.getFirstStartApp()
    override fun setFirstStartApp() {
        publicDataSource.setFirstStartApp()
    }
}