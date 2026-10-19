package org.v_points.di.test

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.subject_start_test.SearchSolutionRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.test.SearchSolutionRepository
import org.v_points.presentation.subjetc_start_test.SubjectStartTestViewModel

@Module
abstract class SubjectStartTestModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectStartTestViewModel::class)
    abstract fun getSubjectStartTestViewModel(subjectStartTestViewModel: SubjectStartTestViewModel): ViewModel

    @Binds
    abstract fun bindsSearchSolutionRepository(searchSolutionRepositoryImpl: SearchSolutionRepositoryImpl): SearchSolutionRepository
}