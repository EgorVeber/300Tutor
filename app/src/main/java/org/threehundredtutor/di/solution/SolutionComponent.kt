package org.threehundredtutor.di.solution

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.ConfigRepository
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory
import org.threehundredtutor.presentation.solution.solution_factory.SolutionFactory

@Component(modules = [SolutionModule::class])
@ScreenScope
interface SolutionComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getTestFactory(solutionFactory: SolutionFactory): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getConfigRepository(configRepository: ConfigRepository): Builder

        fun getSolutionComponentBuilder(): SolutionComponent
    }

    companion object {
        fun createSolutionComponent(): SolutionComponent =
            DaggerSolutionComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getTestFactory(SolutionFactory(DiSetHelper.appComponent.getResourceProvider()))
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getConfigRepository(DiSetHelper.appComponent.getConfigRepository())
                .getSolutionComponentBuilder()
    }
}