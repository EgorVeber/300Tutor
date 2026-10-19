package org.v_points.data.subject_workspace.mappers

import org.v_points.data.subject_workspace.model.HtmlPageResponse
import org.v_points.domain.subject_workspace.models.HtmlPageModel

fun HtmlPageResponse.toHtmlPageModel(): HtmlPageModel = HtmlPageModel(
    html = html.orEmpty(),
    htmlMobile = htmlMobile.orEmpty(),
    htmlPageId = htmlPageId.orEmpty(),
    name = name.orEmpty()
)