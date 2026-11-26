package com.v_points.app.vpoint.participants.di

import androidx.lifecycle.ViewModel
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.vpoint.participants.presentation.ParticipantsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ParticipantsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(ParticipantsViewModel::class)
    abstract fun getParticipantsViewModel(participantsViewModel: ParticipantsViewModel): ViewModel
}