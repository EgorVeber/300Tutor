package org.threehundredtutor.di.modules

import dagger.Binds
import dagger.Module
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.common.utils.ResourceProviderImpl
import org.threehundredtutor.data.AccountManagerRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.domain.AccountManagerRepository
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider

    @Binds
    @Singleton
    abstract fun bindsAccountManagerRepository(accountManagerRepositoryImpl: AccountManagerRepositoryImpl): AccountManagerRepository
}