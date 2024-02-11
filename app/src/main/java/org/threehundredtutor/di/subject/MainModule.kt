package org.threehundredtutor.di.subject

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.main.MainRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.main.MainRepository
import org.threehundredtutor.presentation.main.MainViewModel

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(MainViewModel::class)
    abstract fun getMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @ScreenScope
    abstract fun bindsSubjectRepository(subjectRepositoryImpl: MainRepositoryImpl): MainRepository
}