package com.v_points.app.vpoint.games.di

import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.DiSetHelper
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.di.subject_workspase.SubjectWorkspaceComponent
import com.v_points.app.presentation.common.ResourceProvider
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceBundleModel
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

        @BindsInstance
        fun getGameId(gameId: String): Builder

        fun getGamesComponentBuilder(): GamesComponent
    }

    companion object {
        fun createGamesComponent(gameDayId: String): GamesComponent =
            DaggerGamesComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getGameId(gameDayId)
                .getGamesComponentBuilder()
    }
}
