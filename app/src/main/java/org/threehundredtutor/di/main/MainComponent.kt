package org.threehundredtutor.di.main

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
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
        fun getAccountManager(accountManager: AccountManager): Builder

        fun getMainComponentBuilder(): MainComponent
    }

    companion object {
        fun createHomeComponent(): MainComponent =
            DaggerMainComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getAccountManager(DiSetHelper.appComponent.getAccountManager())
                .getMainComponentBuilder()
    }
}