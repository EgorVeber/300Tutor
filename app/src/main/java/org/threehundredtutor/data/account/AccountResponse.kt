package org.threehundredtutor.data.account

import com.google.gson.annotations.SerializedName

class AccountResponse(
    @SerializedName("isAuthenticated") var isAuthenticated: Boolean?,
    @SerializedName("userId") var userId: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("roles") var roles: ArrayList<String> = arrayListOf(),
    @SerializedName("avatarFileId") var avatarFileId: Int?,
    @SerializedName("name") var name: String?,
    @SerializedName("surname") var surname: String?,
    @SerializedName("phoneNumber") var phoneNumber: String?,
    @SerializedName("noEmail") var noEmail: Boolean?,
    @SerializedName("noPhoneNumber") var noPhoneNumber: Boolean?
)
