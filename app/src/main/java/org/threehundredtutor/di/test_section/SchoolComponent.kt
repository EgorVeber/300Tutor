package org.threehundredtutor.di.test_section

import dagger.BindsInstance
import dagger.Component
import org.threehundredtutor.core.DiSetHelper
import org.threehundredtutor.data.common.data_source.AccountLocalDataSource
import org.threehundredtutor.data.common.data_source.ConfigLocalDataSource
import org.threehundredtutor.data.common.data_source.DomainLocalDataSource
import org.threehundredtutor.data.common.data_source.PublicDataSource
import org.threehundredtutor.data.common.network.ServiceGeneratorProvider
import org.threehundredtutor.data.settings_app.SettingsAppLocalDataSource
import org.threehundredtutor.di.common.ViewModelMapFactory
import org.threehundredtutor.domain.common.AccountAuthorizationInfoRepository
import org.threehundredtutor.presentation.common.ResourceProvider

@Component(modules = [SchoolModule::class])
interface SchoolComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getPublicDataSource(publicDataSource: PublicDataSource): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getDomainLocalDataSource(getDomainLocalDataSource: DomainLocalDataSource): Builder

        @BindsInstance
        fun getSettingsAppLocalDataSource(settingsAppLocalDataSource: SettingsAppLocalDataSource): Builder

        @BindsInstance
        fun getAccountAuthorizationInfoRepository(getAccountAuthorizationInfoRepository: AccountAuthorizationInfoRepository): Builder

        fun getTestSectionComponent(): SchoolComponent
    }

    companion object {
        fun createTestSectionComponent(
        ): SchoolComponent =
            DaggerSchoolComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getDomainLocalDataSource(DiSetHelper.appComponent.getDomainLocalDataSource())
                .getPublicDataSource(DiSetHelper.appComponent.getPublicDataSource())
                .getSettingsAppLocalDataSource(DiSetHelper.appComponent.getSettingsAppLocalDataSource())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getTestSectionComponent()
    }
}