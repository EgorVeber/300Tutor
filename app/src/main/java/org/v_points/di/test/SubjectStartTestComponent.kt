package org.v_points.di.test

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.presentation.common.ResourceProvider
import org.v_points.presentation.subjetc_start_test.SubjectStartTestBundleModel

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