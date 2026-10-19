package com.v_points.app.presentation.subject_detailed.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.v_points.app.presentation.subject_detailed.adapter.SubjectDetailedAdapters.getMenuItemUiModelAdapted
import com.v_points.app.presentation.subject_detailed.ui_models.SubjectDetailedUiItem
import com.v_points.app.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel

class SubjectDetailedManager(
    subjectClickListener: (SubjectMenuItemUiModel) -> Unit,
) : AsyncListDifferDelegationAdapter<SubjectDetailedUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(getMenuItemUiModelAdapted(subjectClickListener))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SubjectDetailedUiItem>() {
            override fun areItemsTheSame(
                oldItem: SubjectDetailedUiItem,
                newItem: SubjectDetailedUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SubjectDetailedUiItem,
                newItem: SubjectDetailedUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}