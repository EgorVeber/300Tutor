package org.threehundredtutor.data.starter

import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.domain.starter.StarterRepository
import javax.inject.Inject

class StarterRepositoryImpl @Inject constructor(
    private val publicDataSource: PublicDataSource,
) : StarterRepository {
    override fun getFirstStartApp(): Boolean = publicDataSource.getFirstStartApp()
    override fun setFirstStartApp() {
        publicDataSource.setFirstStartApp()
    }
}