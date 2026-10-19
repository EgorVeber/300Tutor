package com.v_points.app.di.html_page

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.subject_workspace.SubjectWorkspaceLocalDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.presentation.common.ResourceProvider

@Component(modules = [HtmlPageModule::class])
interface HtmlPageComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getDirectoryId(directoryId: String): Builder

        @BindsInstance
        fun getSubjectWorkspaceLocalDataSource(subjectWorkspaceLocalDataSource: SubjectWorkspaceLocalDataSource): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getSettingsAppRepository(settingsAppRepository: SettingsAppRepository): Builder

        fun getHtmlPageComponent(): HtmlPageComponent
    }

    companion object {
        fun createHtmlPageComponent(
            directoryId: String,
        ): HtmlPageComponent =
            DaggerHtmlPageComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getDirectoryId(directoryId)
                .getSubjectWorkspaceLocalDataSource(DiSetHelper.appComponent.getSubjectWorkspaceLocalDataSource())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getSettingsAppRepository(DiSetHelper.appComponent.getSettingsAppRepository())
                .getHtmlPageComponent()
    }
}