package com.v_points.app.vpoint.game_day_create.di

import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.DiSetHelper
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [CreateGameDayModule::class])
interface CreateGameDayComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getFirestoreInstance(firebaseFirestore: FirebaseFirestore): Builder

        fun getCreateGameDayComponentBuilder(): CreateGameDayComponent
    }

    companion object {
        fun createCreateGameDayComponent(): CreateGameDayComponent =
            DaggerCreateGameDayComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getCreateGameDayComponentBuilder()
    }
}
