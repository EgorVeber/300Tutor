package org.threehundredtutor.di.activity

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.starter.ThemeRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.starter.ThemeRepository
import org.threehundredtutor.presentation.starter.ActivityViewModel

@Module
abstract class ActivityModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(ActivityViewModel::class)
    abstract fun getActivityViewModel(activityViewModel: ActivityViewModel): ViewModel

    @Binds
    abstract fun bindsThemeRepository(themeRepositoryImpl: ThemeRepositoryImpl): ThemeRepository
}