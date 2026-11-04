package com.v_points.app.vpoint.games.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.games.presentation.GamesViewModel

@Module
abstract class GamesModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(GamesViewModel::class)
    abstract fun getGamesViewModel(gamesViewModel: GamesViewModel): ViewModel
}
