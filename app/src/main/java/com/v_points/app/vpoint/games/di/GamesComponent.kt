package com.v_points.app.vpoint.games.di

import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.DiSetHelper
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [GamesModule::class])
interface GamesComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getFirestoreInstance(firebaseFirestore: FirebaseFirestore): Builder

        fun getGamesComponentBuilder(): GamesComponent
    }

    companion object {
        fun createGamesComponent(): GamesComponent =
            DaggerGamesComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getGamesComponentBuilder()
    }
}
