package com.v_points.app.di.settings

import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.ConfigLocalDataSource
import com.v_points.app.data.common.network.ServiceGeneratorProvider
import com.v_points.app.di.common.ViewModelMapFactory
import dagger.BindsInstance
import dagger.Component

@Component(modules = [SettingsModule::class])
interface SettingsComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getServiceGeneratorProvider(serviceGeneratorProvider: ServiceGeneratorProvider): Builder

        @BindsInstance
        fun getConfigLocalDataSource(configLocalDataSource: ConfigLocalDataSource): Builder

        fun getSettingsComponent(): SettingsComponent
    }

    companion object {
        fun createSettingsComponent(): SettingsComponent =
            DaggerSettingsComponent
                .builder()
                .getServiceGeneratorProvider(DiSetHelper.appComponent.getServiceGeneratorProvider())
                .getConfigLocalDataSource(DiSetHelper.appComponent.getConfigLocalDataSource())
                .getSettingsComponent()
    }
}