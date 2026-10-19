package org.v_points.di.solution

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.ScreenScope
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.common.ResourceProvider
import org.v_points.presentation.solution.SolutionParamsDaggerModel
import org.v_points.presentation.solution.solution_factory.SolutionFactory

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

        @BindsInstance
        fun getSettingsAppRepository(settingsAppRepository: SettingsAppRepository): Builder


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
                .getSettingsAppRepository(DiSetHelper.appComponent.getSettingsAppRepository())
                .getSolutionBundleModel(solutionParamsDaggerModel)
                .getSolutionComponentBuilder()
    }
}