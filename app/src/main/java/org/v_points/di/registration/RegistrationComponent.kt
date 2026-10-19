package org.v_points.di.registration

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.common.AccountAuthorizationInfoRepository

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
