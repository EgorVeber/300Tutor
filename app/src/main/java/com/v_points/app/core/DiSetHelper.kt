package com.v_points.app.core

import com.v_points.app.di.common.AppComponent

/**
 * Instanse*
 * Сетим компонент из App, чтоб никто не менял  и не торчала в APP*
 * Временно решение*
 */
object DiSetHelper {
    lateinit var appComponent: AppComponent
        internal set
}