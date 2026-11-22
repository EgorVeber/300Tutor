package com.v_points.app.vpoint.game_create.di

import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.DiSetHelper
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CreateGameModule::class])
interface CreateGameComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getFirestoreInstance(firebaseFirestore: FirebaseFirestore): Builder

        @BindsInstance
        fun getComponentModel(model: CreateGameComponentModel): Builder

        fun getCreateGameComponentBuilder(): CreateGameComponent
    }

    companion object {
        fun createCreateGameComponent(model: CreateGameComponentModel): CreateGameComponent =
            DaggerCreateGameComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getComponentModel(model)
                .getCreateGameComponentBuilder()
    }
}
