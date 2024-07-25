package org.threehundredtutor.data.settings_app.model

import com.google.gson.annotations.SerializedName
import org.threehundredtutor.domain.settings_app.WebAppRoutesModel

class WebAppRoutesResponse(
    @SerializedName("courseShop")
    val courseShop: String?,
    @SerializedName("loginLinkFormat")
    val loginLinkFormat: String?,
    @SerializedName("materials")
    val materials: String?,
    @SerializedName("schedules")
    val schedules: String?,
    @SerializedName("toGroupFormat")
    val toGroupFormat: String?
)

fun WebAppRoutesResponse.toWebAppRoutesModel(): WebAppRoutesModel =
    WebAppRoutesModel(
        courseShop = courseShop.orEmpty(),
        loginLinkFormat = loginLinkFormat.orEmpty(),
        materials = materials.orEmpty(),
        schedules = schedules.orEmpty(),
        toGroupFormat = toGroupFormat.orEmpty(),
    )