package org.v_points.di.subject_tests

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.subject_tests.SubjectTestsRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.subject_tests.SubjectTestsRepository
import org.v_points.presentation.subject_tests.SubjectTestsViewModel

@Module
abstract class SubjectTestsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectTestsViewModel::class)
    abstract fun getSubjectTestsViewModel(subjectTestsViewModel: SubjectTestsViewModel): ViewModel

    @Binds
    abstract fun bindSubjectTestsRepository(subjectTestsRepositoryImpl: SubjectTestsRepositoryImpl): SubjectTestsRepository
}