package org.v_points.domain.subject_workspace.models

import org.v_points.ui_common.EMPTY_STRING

class HtmlPageModel(
    val html: String,
    val htmlMobile: String,
    val htmlPageId: String,
    val name: String
) {
    companion object {
        val EMPTY = HtmlPageModel(
            html = EMPTY_STRING,
            htmlMobile = EMPTY_STRING,
            htmlPageId = EMPTY_STRING,
            name = EMPTY_STRING
        )
    }
}
