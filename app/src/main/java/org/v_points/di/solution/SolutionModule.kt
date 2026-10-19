package org.v_points.di.solution

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.v_points.data.solution.SolutionLocalDataSource
import org.v_points.data.solution.SolutionRepositoryImpl
import org.v_points.di.ScreenScope
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.solution.SolutionRepository
import org.v_points.presentation.solution.SolutionViewModel

@Module
abstract class SolutionModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SolutionViewModel::class)
    abstract fun getSolutionViewModel(solutionViewModel: SolutionViewModel): ViewModel

    @Binds
    abstract fun bindsSolutionRepository(solutionRepositoryImpl: SolutionRepositoryImpl): SolutionRepository

    companion object {
        @Provides
        @ScreenScope
        fun getSolutionLocalDataSource(): SolutionLocalDataSource =
            SolutionLocalDataSource()
    }
}