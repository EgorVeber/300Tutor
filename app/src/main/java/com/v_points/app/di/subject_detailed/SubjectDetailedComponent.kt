package com.v_points.app.di.subject_detailed

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.subject_detailed.SubjectDetailedBundleModel

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