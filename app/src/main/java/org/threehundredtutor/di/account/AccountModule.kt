package org.threehundredtutor.di.account

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.settings_app.SettingsAppRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.settings_app.SettingsAppRepository
import org.threehundredtutor.presentation.account.AccountViewModel
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