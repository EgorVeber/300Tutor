package org.threehundredtutor.di.modules

import dagger.Binds
import dagger.Module
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.common.utils.ResourceProviderImpl
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}