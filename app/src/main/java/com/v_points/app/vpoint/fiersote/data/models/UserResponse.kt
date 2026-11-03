package com.v_points.app.vpoint.fiersote.data.models

import com.google.firebase.firestore.DocumentId

data class UserResponse(
    @DocumentId val id: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val roleId: String? = null
)