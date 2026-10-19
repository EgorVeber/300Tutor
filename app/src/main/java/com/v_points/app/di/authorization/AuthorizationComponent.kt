package com.v_points.app.di.authorization

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.presentation.common.ResourceProvider

/**
 * Отдельная компонента, не SubComponent, свой модуль и со своей scope*
 * Нужно чтоб в компоненте был свой инстанс фабрики вью модели
 */

@Component(modules = [AuthorizationModule::class])
interface AuthorizationComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountAuthorizationInfoRepository(accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getSettingsAppRepository(settingsAppRepository: SettingsAppRepository): Builder

        fun getAuthorizationComponentBuilder(): AuthorizationComponent
    }

    companion object {
        fun createAuthorizationComponent(): AuthorizationComponent =
            DaggerAuthorizationComponent.builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getSettingsAppRepository(DiSetHelper.appComponent.getSettingsAppRepository())
                .getAuthorizationComponentBuilder()
    }
}