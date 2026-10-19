package org.v_points.presentation.subject_workspace.mapper

import org.v_points.domain.subject_workspace.models.DirectoryModel
import org.v_points.presentation.subject_workspace.adapter.DirectoryUiModel

fun DirectoryModel.toDirectoryUiModel(): DirectoryUiModel =
    DirectoryUiModel(
        directoryId = directoryId,
        directoryName = directoryName
    )