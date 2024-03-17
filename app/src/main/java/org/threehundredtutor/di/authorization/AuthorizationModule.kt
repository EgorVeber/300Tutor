package org.threehundredtutor.di.authorization

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.authorization.AuthorizationRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.presentation.authorization.AuthorizationViewModel


/**
 *Учим фабрику создавать по типу вью модели создавать инстанс *
 *Делается для каждой вью модели *
 */
@Module
abstract class AuthorizationModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(AuthorizationViewModel::class)
    abstract fun getAuthorizationViewModel(authorizationViewModel: AuthorizationViewModel): ViewModel

    @Binds
    @ScreenScope
    abstract fun bindsAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository


    // TODO подумать над правильным провайдем. 2 бинда репозитория
    @Binds
    @ScreenScope
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository
}