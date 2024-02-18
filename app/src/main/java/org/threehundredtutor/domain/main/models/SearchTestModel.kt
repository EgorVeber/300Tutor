package org.threehundredtutor.domain.main.models

data class SearchTestModel(
    val count: Int,
    val offSet: Int,
    val totalCount: Int,
    val searchTestInfoModelList: List<SearchTestInfoModel>,
)