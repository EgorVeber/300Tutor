package org.threehundredtutor.presentation.subject_workspace.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.subject_workspace.adapter.WorkspaceDirectoryAdapter.getWorkspaceDirectoryEmptyUiItem

class WorkspaceDirectoryManager(
    workspaceDirectoryUiModelClickListener: (String) -> Unit,
) : AsyncListDifferDelegationAdapter<WorkspaceDirectoryUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(
            WorkspaceDirectoryAdapter.getWorkspaceDirectoryUiModelAdapted(
                workspaceDirectoryUiModelClickListener
            )
        ).addDelegate(getWorkspaceDirectoryEmptyUiItem())
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WorkspaceDirectoryUiItem>() {
            override fun areItemsTheSame(
                oldItem: WorkspaceDirectoryUiItem,
                newItem: WorkspaceDirectoryUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: WorkspaceDirectoryUiItem,
                newItem: WorkspaceDirectoryUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}
