package com.v_points.app.di.subject_workspase

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.subject_workspace.SubjectWorkspaceLocalDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceBundleModel

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