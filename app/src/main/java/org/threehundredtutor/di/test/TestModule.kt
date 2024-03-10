package org.threehundredtutor.di.test

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.presentation.test.TestViewModel

@Module
abstract class TestModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(TestViewModel::class)
    abstract fun getTestViewModel(testViewModel: TestViewModel): ViewModel
}