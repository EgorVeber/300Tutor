package com.v_points.app.fiersote

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class FirestoreModule {
    companion object {
        @Provides
        fun getFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()
    }
}