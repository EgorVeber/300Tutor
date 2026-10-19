package com.v_points.app.di.subject_tests

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory

@Component(modules = [SubjectTestsModule::class])
interface SubjectTestsComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getSubjectId(subjectId: String): Builder

        fun getSubjectTestsComponent(): SubjectTestsComponent
    }

    companion object {
        fun createSubjectTestsComponent(
            subjectId: String,
        ): SubjectTestsComponent =
            DaggerSubjectTestsComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getSubjectId(subjectId)
                .getSubjectTestsComponent()
    }
}