package org.v_points.di.favorites

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.data.favorites.FavoritesRepositoryImpl
import org.v_points.data.solution.SolutionLocalDataSource
import org.v_points.data.solution.SolutionRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.favorites.FavoritesRepository
import org.v_points.domain.solution.SolutionRepository
import org.v_points.presentation.favorites.FavoritesViewModel

@Module
abstract class FavoritesModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(FavoritesViewModel::class)
    abstract fun getFavoritesViewModel(favoritesViewModel: FavoritesViewModel): ViewModel

    @Binds
    abstract fun bindsFavoritesRepository(favoritesRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    abstract fun bindsAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindsSolutionRepository(solutionRepositoryImpl: SolutionRepositoryImpl): SolutionRepository

    companion object {

        // TODo избавиться от хуйни вынести ChangeLikeQuestionUseCase в отдельный сервис и репозиторий
        @Provides
        fun getSolutionLocalDataSource(): SolutionLocalDataSource =
            SolutionLocalDataSource()
    }
}