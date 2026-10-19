package com.v_points.app.di.test_section

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.v_points.app.data.account.AccountRepositoryImpl
import com.v_points.app.data.authorization.AuthorizationRepositoryImpl
import com.v_points.app.data.school.SchoolRepositoryImpl
import com.v_points.app.data.settings_app.SettingsAppRepositoryImpl
import com.v_points.app.di.common.ViewModelInjectMapKey
import com.v_points.app.domain.account.AccountRepository
import com.v_points.app.domain.authorization.AuthorizationRepository
import com.v_points.app.domain.school.SchoolRepository
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.presentation.test_section.SchoolViewModel

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