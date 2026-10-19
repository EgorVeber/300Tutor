package org.v_points.core

import org.v_points.di.common.AppComponent

/**
 * Instanse*
 * Сетим компонент из App, чтоб никто не менял  и не торчала в APP*
 * Временно решение*
 */
object DiSetHelper {
    lateinit var appComponent: AppComponent
        internal set
}