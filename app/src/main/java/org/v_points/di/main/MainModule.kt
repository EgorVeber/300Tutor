package org.v_points.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.data.main.MainRepositoryImpl
import org.v_points.data.settings_app.SettingsAppRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.main.MainRepository
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.main.MainViewModel

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