package com.v_points.app.vpoint.participants.di

import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.DiSetHelper
import com.v_points.app.di.common.ViewModelMapFactory
import com.v_points.app.presentation.common.ResourceProvider
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ParticipantsModule::class])
interface ParticipantsComponent {
    fun viewModelMapFactory(): ViewModelMapFactory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun getResourceProvider(resourceProvider: ResourceProvider): Builder

        @BindsInstance
        fun getFirestoreInstance(firebaseFirestore: FirebaseFirestore): Builder

        fun getParticipantsComponentBuilder(): ParticipantsComponent
    }

    companion object {
        fun createParticipantsComponent(): ParticipantsComponent =
            DaggerParticipantsComponent
                .builder()
                .getResourceProvider(DiSetHelper.appComponent.getResourceProvider())
                .getFirestoreInstance(DiSetHelper.appComponent.getFirestoreInstance())
                .getParticipantsComponentBuilder()
    }
}