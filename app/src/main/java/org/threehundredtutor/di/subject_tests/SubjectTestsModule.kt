package org.threehundredtutor.di.subject_tests

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.subject_tests.SubjectTestsRepositoryImpl
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.subject_tests.SubjectTestsRepository
import org.threehundredtutor.presentation.subject_tests.SubjectTestsViewModel

@Module
abstract class SubjectTestsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectTestsViewModel::class)
    abstract fun getSubjectTestsViewModel(subjectTestsViewModel: SubjectTestsViewModel): ViewModel

    @Binds
    abstract fun bindSubjectTestsRepository(subjectTestsRepositoryImpl: SubjectTestsRepositoryImpl): SubjectTestsRepository
}