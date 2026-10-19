package org.v_points.di.subject_detailed

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.subject_detailed.SubjectDetailedRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.subject_detailed.SubjectDetailedRepository
import org.v_points.presentation.subject_detailed.SubjectDetailedViewModel

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