package org.threehundredtutor.di.main

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.main.MainRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.presentation.main.activate_key_dialog.ActivateKeyViewModel

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