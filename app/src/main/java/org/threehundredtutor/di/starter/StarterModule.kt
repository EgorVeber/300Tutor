package org.threehundredtutor.di.starter

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.authorization.AuthorizationRepositoryImpl
import org.threehundredtutor.data.starter.StarterRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.starter.StarterRepository
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.presentation.starter.StarterViewModel

@Module
abstract class StarterModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(StarterViewModel::class)
    abstract fun getStarterViewModel(starterViewModel: StarterViewModel): ViewModel

    @Binds
    @ScreenScope
    abstract fun bindsAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    @ScreenScope
    abstract fun bindsStarterRepository(starterRepositoryImpl: StarterRepositoryImpl): StarterRepository
}