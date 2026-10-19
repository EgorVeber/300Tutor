package com.v_points.app.di.test_section

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.data_source.DomainLocalDataSource
import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.settings_app.SettingsAppLocalDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import com.v_points.app.presentation.common.ResourceProvider

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