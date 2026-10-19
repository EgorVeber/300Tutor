package com.v_points.app.data.main.response

import com.google.gson.annotations.SerializedName

class EnterGroupResponse(
    @SerializedName("succeeded")
    val succeeded: Boolean?,
    @SerializedName("errorMessage")
    val errorMessage: String?,
    @SerializedName("groupId")
    val groupId: String?,
    @SerializedName("groupName")
    val groupName: String?,
)