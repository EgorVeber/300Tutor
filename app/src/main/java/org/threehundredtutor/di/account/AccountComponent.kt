package org.threehundredtutor.di.account

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.ConfigRepository
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory

@Component(modules = [AccountModule::class])
@ScreenScope
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
        fun getConfigRepository(configRepository: ConfigRepository): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        fun getAccountComponentBuilder(): AccountComponent
    }

    companion object {
        fun createAccountComponent(): AccountComponent =
            DaggerAccountComponent
                .builder()
                .getBuilder(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getConfigRepository(DiSetHelper.appComponent.getConfigRepository())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAccountComponentBuilder()
    }
}