package com.v_points.app.di.activity

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.starter.ThemeRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.starter.ThemeRepository
import com.v_points.app.presentation.starter.ActivityViewModel

@Module
abstract class ActivityModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(ActivityViewModel::class)
    abstract fun getActivityViewModel(activityViewModel: ActivityViewModel): ViewModel

    @Binds
    abstract fun bindsThemeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository
}