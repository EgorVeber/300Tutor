package org.threehundredtutor.di.registration

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ViewModelMapFactory

@Component(modules = [RegistrationInternalModule::class])
interface RegistrationComponent {

    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        fun getRegistrationComponentBuilder(): RegistrationComponent
    }

    companion object {
        fun createRegistrationComponent(): RegistrationComponent =
            DaggerRegistrationComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getRegistrationComponentBuilder()
    }
}
