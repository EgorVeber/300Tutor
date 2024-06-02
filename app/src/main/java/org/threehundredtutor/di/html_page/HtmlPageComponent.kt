package org.threehundredtutor.di.html_page

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.subject_workspace.SubjectWorkspaceLocalDataSource
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.presentation.common.ResourceProvider

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
                .getHtmlPageComponent()
    }
}