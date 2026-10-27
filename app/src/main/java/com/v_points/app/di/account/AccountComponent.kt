package com.v_points.app.di.account

import com.google.firebase.firestore.FirebaseFirestore
import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.settings_app.SettingsAppLocalDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.domain.common.AccountAuthorizationInfoRepository
import com.v_points.app.presentation.common.ResourceProvider

@Component(modules = [AccountModule::class])
interface AccountComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getBuilder(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getAccountAuthorizationInfoRepository(accountAuthorizationInfoRepository: AccountAuthorizationInfoRepository): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        @BindsInstance
        fun getSettingsAppLocalDataSource(settingsAppLocalDataSource: SettingsAppLocalDataSource): Builder

        fun getAccountComponentBuilder(): AccountComponent
    }

    companion object {
        fun createAccountComponent(): AccountComponent =
            DaggerAccountComponent
                .builder()
                .getBuilder(DiSetHelper.appComponent.getResourceProvider())
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAccountAuthorizationInfoRepository(DiSetHelper.appComponent.getAccountAuthorizationInfoRepository())
                .getSettingsAppLocalDataSource(DiSetHelper.appComponent.getSettingsAppLocalDataSource())
                .getAccountComponentBuilder()
    }
}