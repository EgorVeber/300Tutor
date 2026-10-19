package org.v_points.di.html_page

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.subject_workspace.SubjectWorkspaceRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.subject_workspace.SubjectWorkspaceRepository
import org.v_points.presentation.html_page.HtmlPageViewModel

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