package org.threehundredtutor.di.test

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.common.utils.ResourceProvider
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.core.ServiceGeneratorProvider
import org.threehundredtutor.di.ViewModelMapFactory
import org.threehundredtutor.di.solution_history.SolutionHistoryModule
import org.threehundredtutor.presentation.test.TestBundleModel

@Component(modules = [TestModule::class, SolutionHistoryModule::class])
interface TestComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getTestBundleModel(testBundleModel: TestBundleModel): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        fun getTestComponent(): TestComponent
    }

    companion object {
        fun createTestComponent(
            testBundleModel: TestBundleModel,
        ): TestComponent =
            DaggerTestComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getTestBundleModel(testBundleModel)
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getTestComponent()
    }
}