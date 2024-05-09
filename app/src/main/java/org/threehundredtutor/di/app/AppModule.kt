package org.threehundredtutor.di.app

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.common.ResourceProviderImpl
import org.threehundredtutor.data.common.AccountAuthorizationInfoRepositoryImpl
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import javax.inject.Singleton

@Module
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindsResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider

    @Binds
    @Singleton
    abstract fun bindsAccountAuthorizationInfoRepository(accountAuthorizationInfoRepositoryImpl: AccountAuthorizationInfoRepositoryImpl): AccountAuthorizationInfoRepository

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