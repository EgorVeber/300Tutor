package com.v_points.app.di.restore_password

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider

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