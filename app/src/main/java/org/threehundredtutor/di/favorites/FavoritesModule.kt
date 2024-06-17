package org.threehundredtutor.di.favorites

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.favorites.FavoritesRepositoryImpl
import org.threehundredtutor.data.solution.SolutionLocalDataSource
import org.threehundredtutor.data.solution.SolutionRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.favorites.FavoritesRepository
import org.threehundredtutor.domain.solution.SolutionRepository
import org.threehundredtutor.presentation.favorites.FavoritesViewModel

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