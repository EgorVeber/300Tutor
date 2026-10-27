package com.v_points.app.vpoint.fiersote

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides

@Module
abstract class FirestoreModule {
    companion object {
        @Provides
        fun getFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()
    }
}