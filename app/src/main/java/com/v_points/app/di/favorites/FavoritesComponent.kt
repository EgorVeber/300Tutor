package com.v_points.app.di.favorites

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.settings_app.SettingsAppRepository
import com.v_points.app.presentation.common.ResourceProvider

@Component(modules = [FavoritesModule::class])
interface FavoritesComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getSettingsAppRepository(settingsAppRepository: SettingsAppRepository): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder
        fun getFavoritesComponent(): FavoritesComponent
    }

    companion object {
        fun createFavoritesComponent(): FavoritesComponent =
            DaggerFavoritesComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getSettingsAppRepository(DiSetHelper.appComponent.getSettingsAppRepository())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getFavoritesComponent()
    }
}