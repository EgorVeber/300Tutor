package com.v_points.app.di.favorites

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.v_points.app.data.account.AccountRepositoryImpl
import com.v_points.app.data.favorites.FavoritesRepositoryImpl
import com.v_points.app.data.solution.SolutionLocalDataSource
import com.v_points.app.data.solution.SolutionRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.favorites.FavoritesRepository
import com.v_points.app.domain.solution.SolutionRepository
import com.v_points.app.presentation.favorites.FavoritesViewModel

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