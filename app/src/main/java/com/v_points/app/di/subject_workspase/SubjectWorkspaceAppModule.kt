package com.v_points.app.di.subject_workspase

import com.v_points.app.data.subject_workspace.SubjectWorkspaceLocalDataSource
import dagger.Module
import dagger.Provides
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