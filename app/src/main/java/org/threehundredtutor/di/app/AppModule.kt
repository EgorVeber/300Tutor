package org.threehundredtutor.di.app

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.common.ResourceProviderImpl
import org.threehundredtutor.data.common.AccountManagerRepositoryImpl
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.domain.common.AccountManagerRepository
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider

    @Binds
    @Singleton
    abstract fun bindsAccountManagerRepository(accountManagerRepositoryImpl: AccountManagerRepositoryImpl): AccountManagerRepository

    companion object {
        @Provides
        @Singleton
        fun getAccountLocalDataSource(context: Context): AccountLocalDataSource =
            AccountLocalDataSource(context)

        @Provides
        @Singleton
        fun getConfigLocalDataSource(): ConfigLocalDataSource =
            ConfigLocalDataSource()
    }
}