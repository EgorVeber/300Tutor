package org.threehundredtutor.di.solution_history

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.solution_history.SolutionHistoryLocalDataSource
import org.threehundredtutor.data.solution_history.SolutionHistoryRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.solution_history.SolutionHistoryRepository
import org.threehundredtutor.presentation.solution_history.SolutionHistoryViewModel

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