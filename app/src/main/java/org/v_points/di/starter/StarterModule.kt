package org.v_points.di.starter

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.data.authorization.AuthorizationRepositoryImpl
import org.v_points.data.settings_app.SettingsAppRepositoryImpl
import org.v_points.data.starter.StarterRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.authorization.AuthorizationRepository
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.domain.starter.StarterRepository
import org.v_points.presentation.starter.StarterViewModel

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