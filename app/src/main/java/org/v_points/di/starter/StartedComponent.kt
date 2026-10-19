package org.v_points.di.starter

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.AccountLocalDataSource
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.data_source.PublicDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.settings_app.SettingsAppLocalDataSource
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.common.AccountAuthorizationInfoRepository

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