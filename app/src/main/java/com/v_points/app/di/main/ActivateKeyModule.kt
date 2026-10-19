package com.v_points.app.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.main.MainRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.main.MainRepository
import com.v_points.app.presentation.main.activate_key_dialog.ActivateKeyViewModel

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