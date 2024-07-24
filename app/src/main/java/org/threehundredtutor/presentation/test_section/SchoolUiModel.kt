package org.threehundredtutor.presentation.test_section

data class SchoolUiModel(
    val id: String,
    val hostUrl: String,
    val name: String,
    val checked: Boolean
) : SchoolUiItem

interface SchoolUiItem

@JvmInline
value class ErrorSchool(val title: String) : SchoolUiItem