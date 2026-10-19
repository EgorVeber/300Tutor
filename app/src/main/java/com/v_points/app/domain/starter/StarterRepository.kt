package com.v_points.app.domain.starter

interface StarterRepository {
    fun getFirstStartApp(): Boolean
    fun setFirstStartApp()
}
