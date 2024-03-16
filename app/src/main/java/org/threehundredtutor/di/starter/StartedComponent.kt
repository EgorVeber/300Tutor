package org.threehundredtutor.di.starter

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory
import org.threehundredtutor.domain.common.AccountManagerRepository

/**
 * Отдельная компонента, не SubComponent, свой модуль и со своей scope*
 * Нужно чтоб в компоненте был свой инстанс фабрики вью модели
 */

@Component(modules = [StarterModule::class])
@ScreenScope
interface StartedComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountManagerRepository(accountManagerRepository: AccountManagerRepository): Builder

        @BindsInstance
        fun getPublicDataSource(publicDataSource: PublicDataSource): Builder

        fun getStartedComponentBuilder(): StartedComponent
    }

    companion object {
        fun createStartedComponent(): StartedComponent =
            DaggerStartedComponent.builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getPublicDataSource(DiSetHelper.appComponent.getPublicDataSource())
                .getAccountManagerRepository(DiSetHelper.appComponent.getAccountManagerRepository())
                .getStartedComponentBuilder()
    }
}