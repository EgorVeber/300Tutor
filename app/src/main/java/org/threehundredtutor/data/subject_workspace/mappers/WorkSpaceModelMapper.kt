package org.threehundredtutor.data.subject_workspace.mappers

import org.threehundredtutor.data.subject_workspace.model.HtmlPageResponse
import org.threehundredtutor.domain.subject_workspace.models.HtmlPageModel

fun HtmlPageResponse.toHtmlPageModel(): HtmlPageModel = HtmlPageModel(
    html = html.orEmpty(),
    htmlMobile = htmlMobile.orEmpty(),
    htmlPageId = htmlPageId.orEmpty(),
    name = name.orEmpty()
)