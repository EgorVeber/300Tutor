package org.threehundredtutor.common

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import org.threehundredtutor.core.TutorApp
import org.threehundredtutor.presentation.HomeFragment

fun getAppContext(): Context = TutorApp.tutorAppInstance.applicationContext
