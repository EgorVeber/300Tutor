package org.threehundredtutor.domain.subject.models

data class SearchTestInfoModel(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val isGlobal: Boolean,
    val name: String,
    val questionsCount: Int,
    val subject: SubjectModel
)