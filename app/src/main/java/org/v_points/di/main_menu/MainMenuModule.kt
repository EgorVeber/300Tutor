package org.v_points.di.main_menu

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.account.AccountRepository
import org.v_points.presentation.main_menu.MainMenuViewModel

@Module
abstract class MainMenuModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(MainMenuViewModel::class)
    abstract fun getMainMenuViewModel(mainMenuViewModel: MainMenuViewModel): ViewModel

    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}