package org.v_points.data.subject_workspace.mappers

import org.v_points.data.subject_workspace.model.DirectoryResponse
import org.v_points.domain.subject_workspace.models.DirectoryModel
import org.v_points.domain.subject_workspace.models.HtmlPageModel
import org.v_points.ui_common.util.BadRequestException

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