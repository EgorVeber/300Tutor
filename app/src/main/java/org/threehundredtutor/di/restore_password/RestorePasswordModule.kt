package org.threehundredtutor.di.restore_password

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.restore.RestoreRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.restore.RestoreRepository
import org.threehundredtutor.presentation.restore.RestorePasswordViewModel

@Module
abstract class RestorePasswordModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(RestorePasswordViewModel::class)
    abstract fun getRestorePasswordViewModel(restorePasswordViewModel: RestorePasswordViewModel): ViewModel


    @Binds
    abstract fun bindsRestoreRepository(restoreRepositoryImpl: RestoreRepositoryImpl): RestoreRepository
}