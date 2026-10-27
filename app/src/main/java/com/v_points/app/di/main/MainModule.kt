package com.v_points.app.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.main.MainViewModel

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(MainViewModel::class)
    abstract fun getMainViewModel(mainViewModel: MainViewModel): ViewModel

   //@Binds
   //abstract fun bindsSubjectRepository(subjectRepositoryImpl: MainRepositoryImpl): MainRepository

   //@Binds
   //abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

   //@Binds
   //abstract fun bindsSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository
}