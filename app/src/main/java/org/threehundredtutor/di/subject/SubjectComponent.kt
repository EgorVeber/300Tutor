package org.threehundredtutor.di.subject

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ScreenScope
import org.threehundredtutor.di.ViewModelMapFactory


@Component(modules = [SubjectModule::class])
@ScreenScope
interface SubjectComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        fun getSubjectComponentBuilder(): SubjectComponent
    }

    companion object {
        fun createSubjectComponent(): SubjectComponent =
            DaggerSubjectComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getSubjectComponentBuilder()
    }
}