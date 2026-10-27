package com.v_points.app.vpoint.participants.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.main.MainViewModel
import com.v_points.app.vpoint.participants.presentation.ParticipantsViewModel

@Module
abstract class ParticipantsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(ParticipantsViewModel::class)
    abstract fun getParticipantsViewModel(participantsViewModel: ParticipantsViewModel): ViewModel
}