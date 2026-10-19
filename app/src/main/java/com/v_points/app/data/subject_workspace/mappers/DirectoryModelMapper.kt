package com.v_points.app.data.subject_workspace.mappers

import com.v_points.app.data.subject_workspace.model.DirectoryResponse
import com.v_points.app.domain.subject_workspace.models.DirectoryModel
import com.v_points.app.domain.subject_workspace.models.HtmlPageModel
import com.v_points.app.ui_common.util.BadRequestException

fun DirectoryResponse.toDirectoryModel(): DirectoryModel = DirectoryModel(
    directoryId = directoryId ?: throw BadRequestException(),
    directoryName = name.orEmpty(),
    path = path.orEmpty(),
    htmlPageModel = htmlPageResponse?.toHtmlPageModel() ?: HtmlPageModel.EMPTY,
    childDirectoriesList = childDirectoriesList?.map { directoryResponse ->
        directoryResponse.toDirectoryModel()
    }.orEmpty(),
    questionsTypeAndCountList = questionsTypeAndCountList?.map { questionsTypeAndCountResponse ->
        questionsTypeAndCountResponse.toQuestionsTypeAndCountModel()
    }.orEmpty()
)