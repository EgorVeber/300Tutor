package org.v_points.di.subject_workspase

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.subject_workspace.SubjectWorkspaceRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.subject_workspace.SubjectWorkspaceRepository
import org.v_points.presentation.subject_workspace.SubjectWorkspaceViewModel

@Module
abstract class SubjectWorkspaceModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectWorkspaceViewModel::class)
    abstract fun getSubjectWorkspaceViewModel(
        subjectWorkspaceViewModel: SubjectWorkspaceViewModel
    ): ViewModel

    @Binds
    abstract fun bindsSubjectWorkspaceRepository(
        subjectWorkspaceRepositoryImpl: SubjectWorkspaceRepositoryImpl
    ): SubjectWorkspaceRepository
}