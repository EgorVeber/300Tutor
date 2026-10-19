package org.v_points.core

import android.app.Application
import org.v_points.di.common.DaggerAppComponent

class TutorApp : Application() {

    companion object {
        lateinit var tutorAppInstance: TutorApp
    }

    override fun onCreate() {
        super.onCreate()
        tutorAppInstance = this
        initDi()
    }

    private fun initDi() {
        /**Так сказать поддерживаем JSR 330 **/
        DiSetHelper.appComponent = DaggerAppComponent
            .builder()
            .getBuilder(this)
            .getAppComponentBuild()
    }
}