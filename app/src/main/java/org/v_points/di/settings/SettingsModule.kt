package org.v_points.di.settings

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.presentation.settings.SettingsViewModel

@Module

abstract class SettingsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SettingsViewModel::class)
    abstract fun getSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}