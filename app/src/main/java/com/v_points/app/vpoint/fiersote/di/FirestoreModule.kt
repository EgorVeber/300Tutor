package com.v_points.app.vpoint.fiersote.di

import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class FirestoreModule {
    companion object {
        @Provides
        @Singleton
        fun getFirestoreInstance(): FirebaseFirestore = FirebaseFirestore.getInstance()
    }
}