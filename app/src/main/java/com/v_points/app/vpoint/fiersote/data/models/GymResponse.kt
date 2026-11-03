package com.v_points.app.vpoint.fiersote.data.models

import com.google.firebase.firestore.DocumentId

data class GymResponse(
    @DocumentId val id: String? = null,
    val name: String? = null,
    val fullName: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)