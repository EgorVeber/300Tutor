package org.threehundredtutor.core

import org.threehundredtutor.di.common.AppComponent

/**
 * Instanse*
 * Сетим компонент из App, чтоб никто не менял  и не торчала в APP*
 * Временно решение*
 */
object DiSetHelper {
    lateinit var appComponent: AppComponent
        internal set
}