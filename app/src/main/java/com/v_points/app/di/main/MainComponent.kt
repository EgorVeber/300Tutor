package com.v_points.app.di.main

import com.v_points.app.core.DiSetHelper
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [MainModule::class])
interface MainComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder


        fun getMainComponentBuilder(): MainComponent
    }

    companion object {
        fun createHomeComponent(): MainComponent =
            DaggerMainComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getMainComponentBuilder()
    }
}