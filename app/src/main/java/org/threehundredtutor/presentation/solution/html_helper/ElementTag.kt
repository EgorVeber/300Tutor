package org.threehundredtutor.presentation.solution.html_helper

import org.threehundredtutor.common.EMPTY_STRING

enum class ElementTag {
    FILE_IMAGE_TAG, SUP_TAG, SUB_TAG, UNKNOWN;

    companion object {
        private const val FILE_IMAGE_TEXT = "file-image"
        private const val SUP_TEXT = "sup"
        private const val SUB_TEXT = "sub"

        fun getType(tag: String) = when (tag) {
            FILE_IMAGE_TEXT -> FILE_IMAGE_TAG
            SUP_TEXT -> SUP_TAG
            SUB_TEXT -> SUB_TAG
            else -> UNKNOWN
        }

        fun getText(elementTag: ElementTag) {
            when (elementTag) {
                FILE_IMAGE_TAG -> FILE_IMAGE_TEXT
                SUP_TAG -> SUP_TEXT
                SUB_TAG -> SUB_TEXT
                UNKNOWN -> EMPTY_STRING
            }
        }
    }
}

