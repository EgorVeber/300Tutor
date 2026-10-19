package com.v_points.app.di.html_page

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.subject_workspace.SubjectWorkspaceRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.subject_workspace.SubjectWorkspaceRepository
import com.v_points.app.presentation.html_page.HtmlPageViewModel

@Module
abstract class HtmlPageModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(HtmlPageViewModel::class)
    abstract fun getHtmlPageViewModel(
        htmlPageViewModel: HtmlPageViewModel
    ): ViewModel

    @Binds
    abstract fun bindsSubjectWorkspaceRepository(
        subjectWorkspaceRepositoryImpl: SubjectWorkspaceRepositoryImpl
    ): SubjectWorkspaceRepository
}