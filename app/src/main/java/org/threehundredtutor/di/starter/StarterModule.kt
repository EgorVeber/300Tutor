package org.threehundredtutor.di.starter

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.authorization.AuthorizationRepositoryImpl
import org.threehundredtutor.data.starter.StarterRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.domain.starter.StarterRepository
import org.threehundredtutor.presentation.starter.StarterViewModel

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

}