package com.v_points.app.vpoint.fiersote.data.models

import com.google.firebase.firestore.DocumentId

data class StatResponse(
    @DocumentId val id: String? = null,
    val statName: String? = null,
    val weight: Double? = null
)