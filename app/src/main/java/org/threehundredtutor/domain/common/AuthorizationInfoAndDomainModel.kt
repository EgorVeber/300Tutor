package org.threehundredtutor.domain.common

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthorizationInfoAndDomainModel(
    val login: String,
    val password: String,
    val domain: String
) : Parcelable