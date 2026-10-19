package org.v_points.di.subject_workspase

import dagger.Module
import dagger.Provides
import org.v_points.data.subject_workspace.SubjectWorkspaceLocalDataSource
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