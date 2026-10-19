package org.v_points.di.common

import dagger.Binds
import dagger.Module
import org.v_points.data.common.network.ServiceGenerator
import org.v_points.data.common.network.ServiceGeneratorProvider
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindsServiceProvider(serviceGenerator: ServiceGenerator): ServiceGeneratorProvider
}