package org.threehundredtutor.domain.starter

interface StarterRepository {
    fun getFirstStartApp(): Boolean
    fun setFirstStartApp()
}
