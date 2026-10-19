package com.v_points.app.di.main_menu

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.account.AccountRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.presentation.main_menu.MainMenuViewModel

@Module
abstract class MainMenuModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(MainMenuViewModel::class)
    abstract fun getMainMenuViewModel(mainMenuViewModel: MainMenuViewModel): ViewModel

    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}