package com.v_points.app.di.settings

import androidx.lifecycle.ViewModel
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.presentation.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module

abstract class SettingsModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SettingsViewModel::class)
    abstract fun getSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel
}