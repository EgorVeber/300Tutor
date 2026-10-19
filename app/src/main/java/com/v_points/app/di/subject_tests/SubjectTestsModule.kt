package com.v_points.app.di.subject_tests

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.subject_tests.SubjectTestsRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.subject_tests.SubjectTestsRepository
import com.v_points.app.presentation.subject_tests.SubjectTestsViewModel

@Module
abstract class SubjectTestsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectTestsViewModel::class)
    abstract fun getSubjectTestsViewModel(subjectTestsViewModel: SubjectTestsViewModel): ViewModel

    @Binds
    abstract fun bindSubjectTestsRepository(subjectTestsRepositoryImpl: SubjectTestsRepositoryImpl): SubjectTestsRepository
}