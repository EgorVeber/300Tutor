package com.v_points.app.vpoint.participants.data

import com.google.firebase.firestore.PropertyName

data class UserFirestoreModel(
    val id: String = "",
    @get:PropertyName("v_name") @set:PropertyName("v_name") var name: String = "",
    @get:PropertyName("v_lastName") @set:PropertyName("v_lastName") var lastName: String = "",
    @get:PropertyName("v_role_type") @set:PropertyName("v_role_type") var roleType: String = ""
)