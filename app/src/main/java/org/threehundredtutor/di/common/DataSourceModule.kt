package org.threehundredtutor.di.common

import android.content.Context
import dagger.Module
import dagger.Provides
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.DomainLocalDataSource
import org.threehundredtutor.data.common.data_source.PrivateDataSource
import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.data.settings_app.SettingsAppLocalDataSource
import javax.inject.Singleton

@Module
interface DataSourceModule {
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
        fun getDomainLocalDataSource(publicDataSource: PublicDataSource): DomainLocalDataSource =
            DomainLocalDataSource(publicDataSource)

        @Provides
        @Singleton
        fun getPrivateDataSource(context: Context): PrivateDataSource =
            PrivateDataSource(context)

        @Provides
        @Singleton
        fun getPublicDataSource(context: Context): PublicDataSource =
            PublicDataSource(context)

        @Provides
        @Singleton
        fun getSettingsAppLocalDataSource(): SettingsAppLocalDataSource =
            SettingsAppLocalDataSource()
    }
}