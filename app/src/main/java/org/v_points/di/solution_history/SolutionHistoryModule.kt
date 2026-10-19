package org.v_points.di.solution_history

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.v_points.data.solution_history.SolutionHistoryLocalDataSource
import org.v_points.data.solution_history.SolutionHistoryRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.solution_history.SolutionHistoryRepository
import org.v_points.presentation.solution_history.SolutionHistoryViewModel

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