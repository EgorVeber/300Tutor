package com.v_points.app.di.solution_history

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.v_points.app.data.solution_history.SolutionHistoryLocalDataSource
import com.v_points.app.data.solution_history.SolutionHistoryRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.solution_history.SolutionHistoryRepository
import com.v_points.app.presentation.solution_history.SolutionHistoryViewModel

@Module
abstract class SolutionHistoryModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SolutionHistoryViewModel::class)
    abstract fun getSolutionHistoryViewModel(solutionHistoryViewModel: SolutionHistoryViewModel): ViewModel

    @Binds
    abstract fun bindsSolutionHistoryRepository(solutionHistoryRepositoryImpl: SolutionHistoryRepositoryImpl): SolutionHistoryRepository

    companion object {
        @Provides
        fun getSolutionHistoryLocalDataSource(): SolutionHistoryLocalDataSource =
            SolutionHistoryLocalDataSource()
    }
}