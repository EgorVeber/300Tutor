package org.v_points.di.account

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.data.settings_app.SettingsAppRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.account.AccountViewModel
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