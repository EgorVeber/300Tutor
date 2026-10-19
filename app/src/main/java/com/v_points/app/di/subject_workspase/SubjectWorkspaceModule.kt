package com.v_points.app.di.subject_workspase

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.subject_workspace.SubjectWorkspaceRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.subject_workspace.SubjectWorkspaceRepository
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceViewModel

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