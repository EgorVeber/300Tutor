package com.v_points.app.presentation.subject_workspace.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.v_points.app.ui_core.databinding.WorkspaceDirectoryEmptyItemBinding
import com.v_points.app.ui_core.databinding.WorkspaceDirectoryItemBinding

object WorkspaceDirectoryAdapter {
    fun getWorkspaceDirectoryUiModelAdapted(workspaceDirectoryUiModelClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<DirectoryUiModel, WorkspaceDirectoryUiItem, WorkspaceDirectoryItemBinding>(
            { layoutInflater, root ->
                WorkspaceDirectoryItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.root.setOnClickListener {
                workspaceDirectoryUiModelClickListener.invoke(item.directoryId)
            }
            bind { binding.workspaceDirectoryTv.text = item.directoryName }
        }

    fun getWorkspaceDirectoryEmptyUiItem() =
        adapterDelegateViewBinding<EmptyDirectoryUiItem, WorkspaceDirectoryUiItem, WorkspaceDirectoryEmptyItemBinding>(
            { layoutInflater, root ->
                WorkspaceDirectoryEmptyItemBinding.inflate(layoutInflater, root, false)
            }) {}
}