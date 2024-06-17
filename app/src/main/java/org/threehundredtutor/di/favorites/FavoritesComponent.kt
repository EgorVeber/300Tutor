package org.threehundredtutor.di.favorites

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.solution.SolutionLocalDataSource
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.domain.account.AccountRepository
import org.threehundredtutor.domain.settings_app.SettingsAppRepository
import org.threehundredtutor.presentation.common.ResourceProvider

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