package org.threehundredtutor.core

import android.app.Application

class TutorApp : Application() {
    companion object {
        lateinit var tutorAppInstance: TutorApp
    }

    override fun onCreate() {
        super.onCreate()
        tutorAppInstance = this
    }
}