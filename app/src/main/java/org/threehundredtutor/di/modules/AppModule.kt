package org.threehundredtutor.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.common.utils.ResourceProviderImpl
import org.threehundredtutor.data.AccountManagerRepositoryImpl
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

    companion object {
        @Provides
        @Singleton
        fun getSolutionHistoryLocalDataSource(context: Context): AccountManager =
            AccountManager(context)
    }
}