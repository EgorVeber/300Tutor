package org.threehundredtutor.di.subject_detailed

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.presentation.subject_detailed.SubjectDetailedBundleModel

@Component(modules = [SubjectDetailedModule::class])
interface SubjectDetailedComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getSubjectDetailedBundleModel(subjectDetailedBundleModel: SubjectDetailedBundleModel): Builder

        fun getSubjectDetailedComponentBuilder(): SubjectDetailedComponent
    }

    companion object {
        fun createSubjectDetailedComponent(
            subjectDetailedBundleModel: SubjectDetailedBundleModel,
        ): SubjectDetailedComponent =
            DaggerSubjectDetailedComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getSubjectDetailedBundleModel(subjectDetailedBundleModel)
                .getSubjectDetailedComponentBuilder()
    }
}