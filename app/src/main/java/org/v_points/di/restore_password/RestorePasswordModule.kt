package org.v_points.di.restore_password

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.restore.RestoreRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.restore.RestoreRepository
import org.v_points.presentation.restore.RestorePasswordViewModel

@Module
abstract class RestorePasswordModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(RestorePasswordViewModel::class)
    abstract fun getRestorePasswordViewModel(restorePasswordViewModel: RestorePasswordViewModel): ViewModel


    @Binds
    abstract fun bindsRestoreRepository(restoreRepositoryImpl: RestoreRepositoryImpl): RestoreRepository
}