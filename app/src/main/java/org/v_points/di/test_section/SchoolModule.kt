package org.v_points.di.test_section

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import org.v_points.data.account.AccountRepositoryImpl
import org.v_points.data.authorization.AuthorizationRepositoryImpl
import org.v_points.data.school.SchoolRepositoryImpl
import org.v_points.data.settings_app.SettingsAppRepositoryImpl
import org.v_points.di.common.ViewModelInjectMapKey
import org.v_points.domain.account.AccountRepository
import org.v_points.domain.authorization.AuthorizationRepository
import org.v_points.domain.school.SchoolRepository
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.test_section.SchoolViewModel

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