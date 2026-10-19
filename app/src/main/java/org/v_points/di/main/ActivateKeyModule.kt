package org.v_points.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.main.MainRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.main.MainRepository
import org.v_points.presentation.main.activate_key_dialog.ActivateKeyViewModel

@Module
abstract class ActivateKeyModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(ActivateKeyViewModel::class)
    abstract fun getActivateKeyViewModel(activateKeyViewModel: ActivateKeyViewModel): ViewModel

    //TODO добавить свой репозиторий вынести из main сервис по активациии ключа
    @Binds
    abstract fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}