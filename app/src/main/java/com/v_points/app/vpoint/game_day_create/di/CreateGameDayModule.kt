package com.v_points.app.vpoint.game_day_create.di

import androidx.lifecycle.ViewModel
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.game_day_create.presentation.CreateGameDayViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CreateGameDayModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(CreateGameDayViewModel::class)
    abstract fun getCreateGameDayViewModel(createGameDayViewModel: CreateGameDayViewModel): ViewModel
}
