package org.threehundredtutor.di.modules

import dagger.Binds
import dagger.Module
import org.threehundredtutor.data.core.ServiceGenerator
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import javax.inject.Singleton

@Module
abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun bindsServiceProvider(serviceGenerator: ServiceGenerator): ServiceGeneratorProvider
}