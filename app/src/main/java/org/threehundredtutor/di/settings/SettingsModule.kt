package org.threehundredtutor.di.settings

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
import org.threehundredtutor.presentation.settings.SettingsViewModel

@Module

abstract class SettingsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SettingsViewModel::class)
    abstract fun getSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}