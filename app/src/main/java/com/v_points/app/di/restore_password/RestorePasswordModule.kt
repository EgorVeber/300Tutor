package com.v_points.app.di.restore_password

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.restore.RestoreRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.restore.RestoreRepository
import com.v_points.app.presentation.restore.RestorePasswordViewModel

@Module
abstract class RestorePasswordModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(RestorePasswordViewModel::class)
    abstract fun getRestorePasswordViewModel(restorePasswordViewModel: RestorePasswordViewModel): ViewModel


    @Binds
    abstract fun bindsRestoreRepository(restoreRepositoryImpl: RestoreRepositoryImpl): RestoreRepository
}