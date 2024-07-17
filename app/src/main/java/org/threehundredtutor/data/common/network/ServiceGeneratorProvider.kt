package org.threehundredtutor.data.common.network

import kotlin.reflect.KClass

interface ServiceGeneratorProvider {
    fun <T : Any> getService(serviceClass: KClass<T>): T
    fun <T : Any> getService(serviceClass: KClass<T>, baseUrl: String): T
}