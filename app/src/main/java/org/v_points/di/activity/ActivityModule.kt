package org.v_points.di.activity

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.starter.ThemeRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.starter.ThemeRepository
import org.v_points.presentation.starter.ActivityViewModel

@Module
abstract class ActivityModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(ActivityViewModel::class)
    abstract fun getActivityViewModel(activityViewModel: ActivityViewModel): ViewModel

    @Binds
    abstract fun bindsThemeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository
}