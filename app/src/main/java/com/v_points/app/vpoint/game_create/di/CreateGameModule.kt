package com.v_points.app.vpoint.game_create.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.game_create.presentation.CreateGameViewModel

@Module
abstract class CreateGameModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(CreateGameViewModel::class)
    abstract fun getCreateGameViewModel(createGameViewModel: CreateGameViewModel): ViewModel
}
