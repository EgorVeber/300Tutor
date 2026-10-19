package org.v_points.di.main_menu

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.AccountLocalDataSource
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.data_source.PrivateDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.settings_app.SettingsAppRepository
import org.v_points.presentation.common.ResourceProvider

@Component(modules = [MainMenuModule::class])
interface MainMenuComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getPrivateDataSource(privateDataSource: PrivateDataSource): Builder

        @BindsInstance
        fun getSettingsAppRepository(settingsAppRepository: SettingsAppRepository): Builder

        fun getMainMenuComponentBuilder(): MainMenuComponent
    }

    companion object {
        fun createMainMenuComponent(): MainMenuComponent =
            DaggerMainMenuComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getPrivateDataSource(DiSetHelper.appComponent.getPrivateDataSource())
                .getSettingsAppRepository(DiSetHelper.appComponent.getSettingsAppRepository())
                .getMainMenuComponentBuilder()
    }
}