package com.v_points.app.di.common

import dagger.Binds
import dagger.Module
import com.v_points.app.data.account.AccountRepositoryImpl
import com.v_points.app.data.common.AccountAuthorizationInfoRepositoryImpl
import com.v_points.app.data.settings_app.SettingsAppRepositoryImpl
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import com.v_points.app.domain.settings_app.SettingsAppRepository

@Module
abstract class RepoModule {

    @Binds
    abstract fun bindsAccountAuthorizationInfoRepositoryImpl(accountAuthorizationInfoRepositoryImpl: AccountAuthorizationInfoRepositoryImpl): AccountAuthorizationInfoRepository

    @Binds
    abstract fun bindsSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository


    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}