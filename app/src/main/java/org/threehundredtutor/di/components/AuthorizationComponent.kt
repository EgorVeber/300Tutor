package org.threehundredtutor.di.components

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.AccountManager
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory
import org.threehundredtutor.di.modules.AuthorizationModule
import org.threehundredtutor.domain.AccountManagerRepository

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
        fun getBuilder(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountManager(accountManager: AccountManager): Builder

        @BindsInstance
        fun getAccountManagerRepository(accountManagerRepository: AccountManagerRepository): Builder

        fun getAuthorizationComponentBuilder(): AuthorizationComponent
    }

    companion object {
        fun createAuthorizationComponent(): AuthorizationComponent =
            DaggerAuthorizationComponent
                .builder()
                .getBuilder(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountManager(DiSetHelper.appComponent.getAccountManager())
                .getAccountManagerRepository(DiSetHelper.appComponent.getAccountManagerRepository())
                .getAuthorizationComponentBuilder()
    }
}