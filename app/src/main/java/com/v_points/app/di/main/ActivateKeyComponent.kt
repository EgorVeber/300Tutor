package com.v_points.app.di.main

import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import dagger.BindsInstance
import dagger.Component

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