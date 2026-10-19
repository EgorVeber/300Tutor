package com.v_points.app.di.activity

import dagger.BindsInstance
import dagger.Component
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.di.common.ViewModelMapFactory

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getPublicDataSource(publicDataSource: PublicDataSource): Builder

        fun getActivityComponentBuilder(): ActivityComponent
    }

    companion object {
        fun createActivityComponent(): ActivityComponent =
            DaggerActivityComponent.builder()
                .getPublicDataSource(DiSetHelper.appComponent.getPublicDataSource())
                .getActivityComponentBuilder()
    }
}