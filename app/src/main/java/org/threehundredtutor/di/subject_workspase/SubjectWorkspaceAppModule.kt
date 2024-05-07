package org.threehundredtutor.di.subject_workspase

import dagger.Module
import dagger.Provides
import org.threehundredtutor.data.subject_workspace.SubjectWorkspaceLocalDataSource
import javax.inject.Singleton

@Module
abstract class SubjectWorkspaceAppModule {
    companion object {
        @Provides
        @Singleton
        fun getSubjectWorkspaceLocalDataSource(): SubjectWorkspaceLocalDataSource =
            SubjectWorkspaceLocalDataSource()
    }
}