package com.v_points.app.di.registration

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository

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
