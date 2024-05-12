package org.threehundredtutor.di.starter

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository

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

        fun getStartedComponentBuilder(): StartedComponent
    }

    companion object {
        fun createStartedComponent(): StartedComponent =
            DaggerStartedComponent.builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getPublicDataSource(DiSetHelper.appComponent.getPublicDataSource())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getStartedComponentBuilder()
    }
}