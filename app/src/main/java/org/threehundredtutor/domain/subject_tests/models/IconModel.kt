package org.threehundredtutor.domain.subject_tests.models

data class IconModel(
    val fileId: Int,
    val id: String,
    val name: String,
    val setId: String
) {
    companion object {
        val empty = IconModel(
            fileId = -1,
            id = "",
            name = "",
            setId = ""
        )
    }
}