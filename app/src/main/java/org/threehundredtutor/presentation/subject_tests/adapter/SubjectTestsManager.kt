package org.threehundredtutor.presentation.subject_tests.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.subject_tests.adapter.SubjectTestsAdapter.getSubjectTestUiModelAdapted
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestsUiItem

class SubjectTestsManager(
    subjectClickListener: (SubjectTestUiModel) -> Unit,
) : AsyncListDifferDelegationAdapter<SubjectTestsUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getSubjectTestUiModelAdapted(subjectClickListener))
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