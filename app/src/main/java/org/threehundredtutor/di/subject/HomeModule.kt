package org.threehundredtutor.di.subject

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.subject.SubjectRepositoryImpl
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelInjectMapKey
import org.threehundredtutor.domain.subject.SubjectRepository
import org.threehundredtutor.presentation.home.HomeViewModel


@Module
abstract class HomeModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(HomeViewModel::class)
    abstract fun getHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @ScreenScope
    abstract fun bindsSubjectRepository(subjectRepositoryImpl: SubjectRepositoryImpl): SubjectRepository

}