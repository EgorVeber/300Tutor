package org.threehundredtutor.di.test

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.di.ViewModelMapFactory
import org.threehundredtutor.presentation.subjetc_start_test.SubjectStartTestBundleModel

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