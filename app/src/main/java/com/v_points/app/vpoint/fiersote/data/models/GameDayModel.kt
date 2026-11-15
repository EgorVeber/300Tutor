package com.v_points.app.vpoint.fiersote.data.models

import com.google.firebase.firestore.DocumentId

data class GameDayRequest(
    @DocumentId
    val id: String? = null,
    val date: Long,
    val locationId: String
)