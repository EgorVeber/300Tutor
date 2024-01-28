package org.threehundredtutor.di.solution

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
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

        fun getSolutionComponentBuilder(): SolutionComponent
    }

    companion object {
        fun createSolutionComponent(): SolutionComponent =
            DaggerSolutionComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getTestFactory(SolutionFactory(DiSetHelper.appComponent.getResourceProvider()))
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getSolutionComponentBuilder()
    }
}