package org.v_points.di.restore_password

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.presentation.common.ResourceProvider

@Component(modules = [RestorePasswordModule::class])
interface RestorePasswordComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        fun getRestorePasswordComponent(): RestorePasswordComponent
    }

    companion object {
        fun createRestorePasswordComponent(): RestorePasswordComponent =
            DaggerRestorePasswordComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getRestorePasswordComponent()
    }
}