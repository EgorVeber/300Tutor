package org.v_points.di.common

import dagger.Binds
import dagger.Module
import org.v_points.presentation.common.ResourceProvider
import org.v_points.presentation.common.ResourceProviderImpl
import javax.inject.Singleton

@Module
abstract class ResourceModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}