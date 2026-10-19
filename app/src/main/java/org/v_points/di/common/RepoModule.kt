package org.v_points.di.common

import dagger.Binds
import dagger.Module
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.data.common.AccountAuthorizationInfoRepositoryImpl
import org.v_points.data.settings_app.SettingsAppRepositoryImpl
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.common.AccountAuthorizationInfoRepository
import org.v_points.domain.settings_app.SettingsAppRepository

@Module
abstract class RepoModule {

    @Binds
    abstract fun bindsAccountAuthorizationInfoRepositoryImpl(accountAuthorizationInfoRepositoryImpl: AccountAuthorizationInfoRepositoryImpl): AccountAuthorizationInfoRepository

    @Binds
    abstract fun bindsSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository


    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}