package org.v_points.di.html_page

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.subject_workspace.SubjectWorkspaceLocalDataSource
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.common.ResourceProvider

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