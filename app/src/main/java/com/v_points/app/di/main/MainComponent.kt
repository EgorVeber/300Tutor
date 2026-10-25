package com.v_points.app.di.main

import com.google.firebase.firestore.FirebaseFirestore
import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.AccountLocalDataSource
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.data_source.PrivateDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.data.settings_app.SettingsAppLocalDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider

@Component(modules = [MainModule::class])
interface MainComponent {
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
        fun getAccountLocalDataSource(accountLocalDataSource: AccountLocalDataSource): Builder

        @BindsInstance
        fun getPrivateDataSource(privateDataSource: PrivateDataSource): Builder

        @BindsInstance
        fun getFirestoreInstance(firebaseFirestore: FirebaseFirestore): Builder

        @BindsInstance
        fun getSettingsAppLocalDataSource(settingsAppLocalDataSource: SettingsAppLocalDataSource): Builder

        fun getMainComponentBuilder(): MainComponent
    }

    companion object {
        fun createHomeComponent(): MainComponent =
            DaggerMainComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getAccountLocalDataSource(DiSetHelper.appComponent.getAccountLocalDataSource())
                .getPrivateDataSource(DiSetHelper.appComponent.getPrivateDataSource())
                .getSettingsAppLocalDataSource(DiSetHelper.appComponent.getSettingsAppLocalDataSource())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getMainComponentBuilder()
    }
}