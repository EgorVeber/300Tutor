package com.v_points.app.di.activity

import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.DiSetHelper
import com.v_points.app.data.common.data_source.PublicDataSource
import com.v_points.app.di.common.ViewModelMapFactory
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun getPublicDataSource(publicDataSource: PublicDataSource): Builder

        @BindsInstance
        fun getFirestoreInstance(firebaseFirestore: FirebaseFirestore): Builder

        fun getActivityComponentBuilder(): ActivityComponent
    }

    companion object {
        fun createActivityComponent(): ActivityComponent =
            DaggerActivityComponent.builder()
                .getPublicDataSource(DiSetHelper.appComponent.getPublicDataSource())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getActivityComponentBuilder()
    }
}