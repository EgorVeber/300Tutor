package com.v_points.app.di.subject_detailed

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.subject_detailed.SubjectDetailedRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.subject_detailed.SubjectDetailedRepository
import com.v_points.app.presentation.subject_detailed.SubjectDetailedViewModel

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