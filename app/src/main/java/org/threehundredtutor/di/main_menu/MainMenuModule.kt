package org.threehundredtutor.di.main_menu

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.presentation.main_menu.MainMenuViewModel

@Module
abstract class MainMenuModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(MainMenuViewModel::class)
    abstract fun getMainMenuViewModel(mainMenuViewModel: MainMenuViewModel): ViewModel


    // TODO подумать над правильным провайдем. 2 бинда репозитория
    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}