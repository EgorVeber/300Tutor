package org.threehundredtutor.di.solution

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.solution.SolutionRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.presentation.solution.SolutionViewModel

@Module
abstract class SolutionModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SolutionViewModel::class)
    abstract fun getSolutionViewModel(solutionViewModel: SolutionViewModel): ViewModel

    @Binds
    @ScreenScope
    abstract fun bindsSolutionRepository(solutionRepositoryImpl: SolutionRepositoryImpl): SolutionRepository
}