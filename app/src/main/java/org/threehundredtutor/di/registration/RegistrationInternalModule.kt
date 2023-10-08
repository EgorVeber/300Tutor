package org.threehundredtutor.di.registration

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.registration.RegistrationRepositoryImpl
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.registration.repository.RegistrationRepository
import org.threehundredtutor.presentation.registration.viewmodel.RegistrationViewModel

@Module
internal interface RegistrationInternalModule {

    @Binds
    @IntoMap
    @ViewModelInjectMapKey(RegistrationViewModel::class)
    abstract fun getRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    fun bindRegistrationRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository

}
