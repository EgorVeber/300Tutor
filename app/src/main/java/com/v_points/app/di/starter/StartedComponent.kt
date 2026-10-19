package com.v_points.app.di.starter

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.settings_app.SettingsAppLocalDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository

@Component(modules = [StarterModule::class])
interface StartedComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getPublicDataSource(publicDataSource: PublicDataSource): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getAccountAuthorizationInfoRepository(accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository): Builder

        @BindsInstance
        fun getSettingsAppLocalDataSource(settingsAppLocalDataSource: SettingsAppLocalDataSource): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        fun getStartedComponentBuilder(): StartedComponent
    }

    companion object {
        fun createStartedComponent(): StartedComponent =
            DaggerStartedComponent.builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getPublicDataSource(DiSetHelper.appComponent.getPublicDataSource())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getSettingsAppLocalDataSource(DiSetHelper.appComponent.getSettingsAppLocalDataSource())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getStartedComponentBuilder()
    }
}