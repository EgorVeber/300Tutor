package org.v_points.domain.starter

interface StarterRepository {
    fun getFirstStartApp(): Boolean
    fun setFirstStartApp()
}
