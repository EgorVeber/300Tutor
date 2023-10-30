package org.threehundredtutor.presentation.solution.html_helper

import android.view.Gravity

enum class GravityAlign {
    LEFT, RIGHT, CENTER;

    companion object {
        private const val RIGHT_TEXT = "right"
        private const val LEFT_TEXT = "left"
        private const val CENTER_TEXT = "center"

        fun getGravity(align: String): GravityAlign {
            return when (align) {
                RIGHT_TEXT -> RIGHT
                LEFT_TEXT -> LEFT
                CENTER_TEXT -> CENTER
                else -> LEFT
            }
        }

        fun GravityAlign.getGravity() =
            when (this) {
                LEFT -> Gravity.START
                RIGHT -> Gravity.END
                CENTER -> Gravity.CENTER
            }
    }
}


