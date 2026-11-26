package com.v_points.app.di.registration

import androidx.lifecycle.ViewModel
import com.v_points.app.data.registration.RegistrationRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.registration.repository.RegistrationRepository
import com.v_points.app.presentation.registration.viewmodel.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface RegistrationInternalModule {

    @Binds
    @IntoMap
    @ViewModelInjectMapKey(RegistrationViewModel::class)
    abstract fun getRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    fun bindRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository
}