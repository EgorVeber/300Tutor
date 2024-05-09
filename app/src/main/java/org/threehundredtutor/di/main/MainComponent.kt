package org.threehundredtutor.di.main

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.ConfigRepository
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory

@Component(modules = [MainModule::class])
@ScreenScope
interface MainComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getConfigRepository(configRepository: ConfigRepository): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        fun getMainComponentBuilder(): MainComponent
    }

    companion object {
        fun createHomeComponent(): MainComponent =
            DaggerMainComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getConfigRepository(DiSetHelper.appComponent.getConfigRepository())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getMainComponentBuilder()
    }
}