package org.threehundredtutor.di.common

import dagger.Binds
import dagger.Module
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.common.ResourceProviderImpl
import javax.inject.Singleton

@Module
abstract class ResourceModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider
}