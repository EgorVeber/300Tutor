package org.v_points.di.solution_history

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.presentation.common.ResourceProvider

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