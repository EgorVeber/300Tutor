package com.v_points.app.di.starter

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.account.AccountRepositoryImpl
import com.v_points.app.data.authorization.AuthorizationRepositoryImpl
import com.v_points.app.data.settings_app.SettingsAppRepositoryImpl
import com.v_points.app.data.starter.StarterRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.authorization.AuthorizationRepository
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.domain.starter.StarterRepository
import com.v_points.app.presentation.starter.StarterViewModel

@Module
abstract class StarterModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(StarterViewModel::class)
    abstract fun getStarterViewModel(starterViewModel: StarterViewModel): ViewModel

    @Binds
    abstract fun bindsAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    abstract fun bindsStarterRepository(starterRepositoryImpl: StarterRepositoryImpl): StarterRepository

    @Binds
    abstract fun bindsAccountRepository(accountRepository: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindsSettingsAppRepository(accountRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository

}