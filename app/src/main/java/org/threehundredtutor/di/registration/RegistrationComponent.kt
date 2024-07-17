package org.threehundredtutor.di.registration

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository

@Component(modules = [RegistrationInternalModule::class])
interface RegistrationComponent {

    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountAuthorizationInfoRepository(accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository): Builder
        fun getRegistrationComponentBuilder(): RegistrationComponent
    }

    companion object {
        fun createRegistrationComponent(): RegistrationComponent =
            DaggerRegistrationComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getRegistrationComponentBuilder()
    }
}
