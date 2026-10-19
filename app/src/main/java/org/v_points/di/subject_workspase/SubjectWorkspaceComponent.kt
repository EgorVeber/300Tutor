package org.v_points.di.subject_workspase

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.subject_workspace.SubjectWorkspaceLocalDataSource
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.presentation.subject_workspace.SubjectWorkspaceBundleModel

@Component(modules = [SubjectWorkspaceModule::class])
interface SubjectWorkspaceComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getSubjectWorkspaceBundleModel(subjectWorkspaceBundleModel: SubjectWorkspaceBundleModel): Builder

        @BindsInstance
        fun getSubjectWorkspaceLocalDataSource(subjectWorkspaceLocalDataSource: SubjectWorkspaceLocalDataSource): Builder

        fun getSubjectWorkspaceComponentBuilder(): SubjectWorkspaceComponent
    }

    companion object {
        fun createSubjectWorkspaceComponent(
            subjectWorkspaceBundleModel: SubjectWorkspaceBundleModel,
        ): SubjectWorkspaceComponent =
            DaggerSubjectWorkspaceComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getSubjectWorkspaceBundleModel(subjectWorkspaceBundleModel)
                .getSubjectWorkspaceLocalDataSource(DiSetHelper.appComponent.getSubjectWorkspaceLocalDataSource())
                .getSubjectWorkspaceComponentBuilder()
    }
}