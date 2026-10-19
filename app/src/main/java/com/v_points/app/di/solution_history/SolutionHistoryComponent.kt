package com.v_points.app.di.solution_history

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider

@Component(modules = [SolutionHistoryModule::class])
interface SolutionHistoryComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder
        fun getSolutionHistoryComponentBuilder(): SolutionHistoryComponent
    }

    companion object {
        fun createSolutionHistoryComponent(): SolutionHistoryComponent =
            DaggerSolutionHistoryComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getSolutionHistoryComponentBuilder()
    }
}