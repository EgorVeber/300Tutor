package org.v_points.di.test_section

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.AccountLocalDataSource
import org.v_points.data.common.data_source.ConfigLocalDataSource
import org.v_points.data.common.data_source.DomainLocalDataSource
import org.v_points.data.common.data_source.PublicDataSource
import org.v_points.data.common.network.ServiceGeneratorProvider
import org.v_points.data.settings_app.SettingsAppLocalDataSource
import org.v_points.di.common.ViewModelMapFactory
import org.v_points.domain.common.AccountAuthorizationInfoRepository
import org.v_points.presentation.common.ResourceProvider

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