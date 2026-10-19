package com.v_points.app.di.authorization

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.authorization.AuthorizationRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.authorization.AuthorizationRepository
import com.v_points.app.presentation.authorization.AuthorizationViewModel

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