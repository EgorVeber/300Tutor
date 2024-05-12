package org.threehundredtutor.di.main

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.PrivateDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.presentation.common.ResourceProvider

@Component(modules = [MainModule::class])
interface MainComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getPrivateDataSource(privateDataSource: PrivateDataSource): Builder

        fun getMainComponentBuilder(): MainComponent
    }

    companion object {
        fun createHomeComponent(): MainComponent =
            DaggerMainComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getPrivateDataSource(DiSetHelper.appComponent.getPrivateDataSource())
                .getMainComponentBuilder()
    }
}