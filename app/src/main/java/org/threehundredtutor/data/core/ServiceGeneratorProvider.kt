package org.threehundredtutor.data.core

import kotlin.reflect.KClass
interface ServiceGeneratorProvider {
    fun <T : Any> getService(serviceClass: KClass<T>): T
}