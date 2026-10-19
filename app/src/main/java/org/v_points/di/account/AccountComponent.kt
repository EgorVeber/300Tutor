package org.v_points.di.account

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.AccountLocalDataSource
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.settings_app.SettingsAppLocalDataSource
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.common.AccountAuthorizationInfoRepository
import org.v_points.presentation.common.ResourceProvider

@Component(modules = [AccountModule::class])
interface AccountComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getAccountAuthorizationInfoRepository(accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getSettingsAppLocalDataSource(settingsAppLocalDataSource: SettingsAppLocalDataSource): Builder

        fun getAccountComponentBuilder(): AccountComponent
    }

    companion object {
        fun createAccountComponent(): AccountComponent =
            DaggerAccountComponent
                .builder()
                .getBuilder(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getSettingsAppLocalDataSource(DiSetHelper.appComponent.getSettingsAppLocalDataSource())
                .getAccountComponentBuilder()
    }
}