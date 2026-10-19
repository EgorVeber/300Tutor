package com.v_points.app.di.test

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.subject_start_test.SearchSolutionRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.test.SearchSolutionRepository
import com.v_points.app.presentation.subjetc_start_test.SubjectStartTestViewModel

@Module
abstract class SubjectStartTestModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SubjectStartTestViewModel::class)
    abstract fun getSubjectStartTestViewModel(subjectStartTestViewModel: SubjectStartTestViewModel): ViewModel

    @Binds
    abstract fun bindsSearchSolutionRepository(searchSolutionRepositoryImpl: SearchSolutionRepositoryImpl): SearchSolutionRepository
}