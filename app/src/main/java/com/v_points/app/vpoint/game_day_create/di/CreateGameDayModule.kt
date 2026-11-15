package com.v_points.app.vpoint.game_day_create.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.game_day_create.presentation.CreateGameDayViewModel

@Module
abstract class CreateGameDayModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(CreateGameDayViewModel::class)
    abstract fun getCreateGameDayViewModel(createGameDayViewModel: CreateGameDayViewModel): ViewModel
}
