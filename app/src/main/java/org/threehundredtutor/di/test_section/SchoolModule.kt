package org.threehundredtutor.di.test_section

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.threehundredtutor.data.account.AccountRepositoryImpl
import org.threehundredtutor.data.authorization.AuthorizationRepositoryImpl
import org.threehundredtutor.data.school.SchoolRepositoryImpl
import org.threehundredtutor.data.settings_app.SettingsAppRepositoryImpl
import org.threehundredtutor.di.common.ViewModelInjectMapKey
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.authorization.AuthorizationRepository
import org.threehundredtutor.domain.school.SchoolRepository
import org.threehundredtutor.domain.settings_app.SettingsAppRepository
import org.threehundredtutor.presentation.test_section.SchoolViewModel

@Module
abstract class SchoolModule {
    @Binds
    @IntoMap
    @ViewModelInjectMapKey(SchoolViewModel::class)
    abstract fun getSchoolViewModel(schoolViewModel: SchoolViewModel): ViewModel

    @Binds
    abstract fun bindSchoolRepository(schoolRepositoryImpl: SchoolRepositoryImpl): SchoolRepository

    @Binds
    abstract fun bindSettingsAppRepository(settingsAppRepositoryImpl: SettingsAppRepositoryImpl): SettingsAppRepository

    @Binds
    abstract fun bindAccountRepository(accountRepositoryImpl: AccountRepositoryImpl): AccountRepository

    @Binds
    abstract fun bindsAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository
}