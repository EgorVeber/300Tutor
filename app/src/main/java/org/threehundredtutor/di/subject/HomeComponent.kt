package org.threehundredtutor.di.subject

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory


@Component(modules = [HomeModule::class])
@ScreenScope
interface HomeComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        fun getHomeComponentBuilder(): HomeComponent
    }

    companion object {
        fun createHomeComponent(): HomeComponent =
            DaggerHomeComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getHomeComponentBuilder()
    }
}