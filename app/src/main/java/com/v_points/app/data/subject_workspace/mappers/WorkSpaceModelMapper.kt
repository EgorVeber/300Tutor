package com.v_points.app.data.subject_workspace.mappers

import com.v_points.app.data.subject_workspace.model.HtmlPageResponse
import com.v_points.app.domain.subject_workspace.models.HtmlPageModel

fun HtmlPageResponse.toHtmlPageModel(): HtmlPageModel = HtmlPageModel(
    html = html.orEmpty(),
    htmlMobile = htmlMobile.orEmpty(),
    htmlPageId = htmlPageId.orEmpty(),
    name = name.orEmpty()
)