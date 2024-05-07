package org.threehundredtutor.di.test

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.subject_start_test.SearchSolutionRepositoryImpl
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.test.SearchSolutionRepository
import org.threehundredtutor.presentation.subjetc_start_test.SubjectStartTestViewModel

@Module
abstract class SubjectStartTestModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectStartTestViewModel::class)
    abstract fun getSubjectStartTestViewModel(subjectStartTestViewModel: SubjectStartTestViewModel): ViewModel

    @Binds
    abstract fun bindsSearchSolutionRepository(searchSolutionRepositoryImpl: SearchSolutionRepositoryImpl): SearchSolutionRepository
}