package org.v_points.di.authorization

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.authorization.AuthorizationRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.authorization.AuthorizationRepository
import org.v_points.presentation.authorization.AuthorizationViewModel

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
    abstract fun bindsAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository
}