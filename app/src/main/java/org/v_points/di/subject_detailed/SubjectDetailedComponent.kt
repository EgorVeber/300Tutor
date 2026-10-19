package org.v_points.di.subject_detailed

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.presentation.subject_detailed.SubjectDetailedBundleModel

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