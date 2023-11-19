package org.threehundredtutor.di.solution_history

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory

@Component(modules = [SolutionHistoryModule::class])
@ScreenScope
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