package org.threehundredtutor.di.solution

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.presentation.common.ResourceProvider
import org.threehundredtutor.presentation.solution.SolutionParamsDaggerModel
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
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getSolutionBundleModel(solutionParamsDaggerModel: SolutionParamsDaggerModel): Builder

        fun getSolutionComponentBuilder(): SolutionComponent
    }

    companion object {
        fun createSolutionComponent(solutionParamsDaggerModel: SolutionParamsDaggerModel): SolutionComponent =
            DaggerSolutionComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getTestFactory(SolutionFactory(DiSetHelper.appComponent.getResourceProvider()))
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getSolutionBundleModel(solutionParamsDaggerModel)
                .getSolutionComponentBuilder()
    }
}