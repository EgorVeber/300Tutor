package org.threehundredtutor.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.main.MainRepositoryImpl
import org.threehundredtutor.data.settings_app.SettingsAppRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.domain.settings_app.SettingsAppRepository
import org.threehundredtutor.presentation.main.MainViewModel

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(MainViewModel::class)
    abstract fun getMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    abstract fun bindsSubjectRepository(subjectRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindsSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository
}