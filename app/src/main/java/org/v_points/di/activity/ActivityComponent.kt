package org.v_points.di.activity

import dagger.BindsInstance
import dagger.Component
import org.v_points.core.DiSetHelper
import org.v_points.data.common.data_source.PublicDataSource
import org.v_points.di.common.ViewModelMapFactory

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