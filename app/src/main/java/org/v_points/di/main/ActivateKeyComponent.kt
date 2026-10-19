package org.v_points.di.main

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.presentation.common.ResourceProvider

@Component(modules = [ActivateKeyModule::class])
interface ActivateKeyComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        fun getActivateKeyComponent(): ActivateKeyComponent
    }

    companion object {
        fun createActivateKeyComponent(): ActivateKeyComponent =
            DaggerActivateKeyComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getActivateKeyComponent()
    }
}