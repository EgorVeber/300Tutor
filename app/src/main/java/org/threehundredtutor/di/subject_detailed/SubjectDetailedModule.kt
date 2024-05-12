package org.threehundredtutor.di.subject_detailed

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.subject_detailed.SubjectDetailedRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.subject_detailed.SubjectDetailedRepository
import org.threehundredtutor.presentation.subject_detailed.SubjectDetailedViewModel

@Module
abstract class SubjectDetailedModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectDetailedViewModel::class)
    abstract fun getSubjectDetailedViewModel(
        subjectDetailedViewModel: SubjectDetailedViewModel
    ): ViewModel

    @Binds
    abstract fun bindsSubjectDetailedRepository(
        subjectDetailedRepositoryImpl: SubjectDetailedRepositoryImpl
    ): SubjectDetailedRepository
}