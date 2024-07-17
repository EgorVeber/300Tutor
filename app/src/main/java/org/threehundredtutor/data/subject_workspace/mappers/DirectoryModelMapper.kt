package org.threehundredtutor.data.subject_workspace.mappers

import org.threehundredtutor.data.subject_workspace.model.DirectoryResponse
import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.domain.subject_workspace.models.HtmlPageModel
import org.threehundredtutor.ui_common.util.BadRequestException

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