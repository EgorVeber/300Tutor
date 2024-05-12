package org.threehundredtutor.di.common

import android.content.Context
import dagger.Module
import dagger.Provides
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.PrivateDataSource
import org.threehundredtutor.data.common.data_source.PublicDataSource
import javax.inject.Singleton

@Module
abstract class DataSourceModule {
    companion object {
        @Provides
        @Singleton
        fun getAccountLocalDataSource(): AccountLocalDataSource = AccountLocalDataSource()

        @Provides
        @Singleton
        fun getConfigLocalDataSource(): ConfigLocalDataSource =
            ConfigLocalDataSource()

        @Provides
        @Singleton
        fun getPrivateDataSource(context: Context): PrivateDataSource =
            PrivateDataSource(context)

        @Provides
        @Singleton
        fun getPublicDataSource(context: Context): PublicDataSource =
            PublicDataSource(context)
    }
}