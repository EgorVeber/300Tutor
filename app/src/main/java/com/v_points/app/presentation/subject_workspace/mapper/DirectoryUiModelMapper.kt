package com.v_points.app.presentation.subject_workspace.mapper

import com.v_points.app.domain.subject_workspace.models.DirectoryModel
import com.v_points.app.presentation.subject_workspace.adapter.DirectoryUiModel

fun DirectoryModel.toDirectoryUiModel(): DirectoryUiModel =
    DirectoryUiModel(
        directoryId = directoryId,
        directoryName = directoryName
    )