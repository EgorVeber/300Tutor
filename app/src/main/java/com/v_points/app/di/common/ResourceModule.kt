package com.v_points.app.di.common

import dagger.Binds
import dagger.Module
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.common.ResourceProviderImpl
import javax.inject.Singleton

@Module
abstract class ResourceModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}