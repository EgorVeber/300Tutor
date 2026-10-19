package com.v_points.app.di.test

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.subjetc_start_test.SubjectStartTestBundleModel

@Component(modules = [SubjectStartTestModule::class])
interface SubjectStartTestComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getTestBundleModel(subjectStartTestBundleModel: SubjectStartTestBundleModel): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        fun getSubjectStartTestComponent(): SubjectStartTestComponent
    }

    companion object {
        fun createTestComponent(
            subjectStartTestBundleModel: SubjectStartTestBundleModel,
        ): SubjectStartTestComponent =
            DaggerSubjectStartTestComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getTestBundleModel(subjectStartTestBundleModel)
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getSubjectStartTestComponent()
    }
}