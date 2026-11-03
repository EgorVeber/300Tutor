
package com.v_points.app.vpoint.fiersote.data.models

import com.google.firebase.firestore.DocumentId

data class RoleResponse(
    @DocumentId val id: String? = null,
    val roleType: String? = null
)