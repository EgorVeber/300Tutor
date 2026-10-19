package org.v_points.di.registration

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.registration.RegistrationRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.registration.repository.RegistrationRepository
import org.v_points.presentation.registration.viewmodel.RegistrationViewModel

@Module
internal interface RegistrationInternalModule {

    @Binds
    @IntoMap
    @ViewModelInjectMapKey(RegistrationViewModel::class)
    abstract fun getRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    fun bindRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository
}