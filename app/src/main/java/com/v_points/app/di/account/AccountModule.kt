package com.v_points.app.di.account

import androidx.lifecycle.ViewModel
import com.v_points.app.data.account.AccountRepositoryImpl
import com.v_points.app.data.settings_app.SettingsAppRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.presentation.account.AccountViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module

abstract class AccountModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(AccountViewModel::class)
    abstract fun getAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindsSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository
}