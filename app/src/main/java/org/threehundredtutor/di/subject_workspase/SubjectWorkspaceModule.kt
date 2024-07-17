package org.threehundredtutor.di.subject_workspase

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.subject_workspace.SubjectWorkspaceRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.subject_workspace.SubjectWorkspaceRepository
import org.threehundredtutor.presentation.subject_workspace.SubjectWorkspaceViewModel

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