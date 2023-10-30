package org.threehundredtutor.data.registration.models

import com.google.gson.annotations.SerializedName

class StudentRegisterAndSignInResponse(
    @SerializedName("succeeded")
    val succeded: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("studentId")
    val studentId: String?
)
