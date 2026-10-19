package com.v_points.app.di.solution

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.ScreenScope
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.solution.SolutionParamsDaggerModel
import com.v_points.app.presentation.solution.solution_factory.SolutionFactory

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