package com.v_points.app.di.common

import dagger.Binds
import dagger.Module
import com.v_points.app.data.common.network.ServiceGenerator
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindsServiceProvider(serviceGenerator: ServiceGenerator): ServiceGeneratorProvider
}