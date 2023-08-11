package org.threehundredtutor.common

import android.content.Context
import org.threehundredtutor.core.TutorApp

fun getAppContext(): Context = TutorApp.tutorAppInstance.applicationContext
