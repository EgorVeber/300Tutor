package org.threehundredtutor.di.html_page

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.subject_workspace.SubjectWorkspaceRepositoryImpl
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.subject_workspace.SubjectWorkspaceRepository
import org.threehundredtutor.presentation.html_page.HtmlPageViewModel

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