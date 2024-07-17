package org.threehundredtutor.di.common

import dagger.Binds
import dagger.Module
import org.threehundredtutor.data.common.network.ServiceGenerator
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindsServiceProvider(serviceGenerator: ServiceGenerator): ServiceGeneratorProvider
}