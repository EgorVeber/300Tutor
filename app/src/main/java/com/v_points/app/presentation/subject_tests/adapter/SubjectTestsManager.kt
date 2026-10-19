package com.v_points.app.presentation.subject_tests.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.v_points.app.presentation.subject_tests.adapter.SubjectTestsAdapter.getSubjectEmptyTestsAdapted
import com.v_points.app.presentation.subject_tests.adapter.SubjectTestsAdapter.getSubjectTestUiModelAdapted
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestUiModel
import com.v_points.app.presentation.subject_tests.ui_models.SubjectTestsUiItem

class SubjectTestsManager(
    subjectClickListener: (SubjectTestUiModel) -> Unit,
) : AsyncListDifferDelegationAdapter<SubjectTestsUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getSubjectTestUiModelAdapted(subjectClickListener))
            .addDelegate(getSubjectEmptyTestsAdapted())
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SubjectTestsUiItem>() {
            override fun areItemsTheSame(
                oldItem: SubjectTestsUiItem,
                newItem: SubjectTestsUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SubjectTestsUiItem,
                newItem: SubjectTestsUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}