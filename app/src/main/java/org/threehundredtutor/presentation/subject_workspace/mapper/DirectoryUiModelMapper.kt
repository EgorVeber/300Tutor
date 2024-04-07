package org.threehundredtutor.presentation.subject_workspace.mapper

import org.threehundredtutor.domain.subject_workspace.models.DirectoryModel
import org.threehundredtutor.presentation.subject_workspace.adapter.DirectoryUiModel

fun DirectoryModel.toDirectoryUiModel(): DirectoryUiModel =
    DirectoryUiModel(
        directoryId = directoryId,
        directoryName = directoryName
    )