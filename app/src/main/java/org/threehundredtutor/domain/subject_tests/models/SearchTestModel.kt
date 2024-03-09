package org.threehundredtutor.domain.subject_tests.models

data class SearchTestModel(
    val count: Int,
    val offSet: Int,
    val totalCount: Int,
    val searchTestInfoModelList: List<SearchTestInfoModel>,
)