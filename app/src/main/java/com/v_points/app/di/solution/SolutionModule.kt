package com.v_points.app.di.solution

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.v_points.app.data.solution.SolutionLocalDataSource
import com.v_points.app.data.solution.SolutionRepositoryImpl
import com.v_points.app.di.ScreenScope
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.presentation.solution.SolutionViewModel

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