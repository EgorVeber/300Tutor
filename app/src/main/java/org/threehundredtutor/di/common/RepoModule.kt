package org.threehundredtutor.di.common

import dagger.Binds
import dagger.Module
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.common.AccountAuthorizationInfoRepositoryImpl
import org.threehundredtutor.data.settings_app.SettingsAppRepositoryImpl
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import org.threehundredtutor.domain.settings_app.SettingsAppRepository

@Module
abstract class RepoModule {

    @Binds
    abstract fun bindsAccountAuthorizationInfoRepositoryImpl(accountAuthorizationInfoRepositoryImpl: AccountAuthorizationInfoRepositoryImpl): AccountAuthorizationInfoRepository

    @Binds
    abstract fun bindsSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository


    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}