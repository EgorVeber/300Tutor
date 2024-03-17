package org.threehundredtutor.di.authorization

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory
import org.threehundredtutor.domain.common.AccountManagerRepository

/**
 * Отдельная компонента, не SubComponent, свой модуль и со своей scope*
 * Нужно чтоб в компоненте был свой инстанс фабрики вью модели
 */

@Component(modules = [AuthorizationModule::class])
@ScreenScope
interface AuthorizationComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountManager(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getAccountManagerRepository(accountManagerRepository: AccountManagerRepository): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        fun getAuthorizationComponentBuilder(): AuthorizationComponent
    }

    companion object {
        fun createAuthorizationComponent(): AuthorizationComponent =
            DaggerAuthorizationComponent.builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountManager(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getAccountManagerRepository(DiSetHelper.appComponent.getAccountManagerRepository())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAuthorizationComponentBuilder()
    }
}