package org.threehundredtutor.presentation.subject.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.subject.adapter.SubjectAdapters.getSubjectHeaderUiItemAdapter
import org.threehundredtutor.presentation.subject.adapter.SubjectAdapters.getSubjectTestUiModelAdapted
import org.threehundredtutor.presentation.subject.adapter.SubjectAdapters.getSubjectUiModelAdapted
import org.threehundredtutor.presentation.subject.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.subject.ui_models.SubjectUiItem
import org.threehundredtutor.presentation.subject.ui_models.SubjectUiModel

class SubjectManager(
    subjectClickListener: (SubjectUiModel) -> Unit,
    subjectTestClickListener: (SubjectTestUiModel) -> Unit,
) : AsyncListDifferDelegationAdapter<SubjectUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getSubjectHeaderUiItemAdapter())
            .addDelegate(getSubjectUiModelAdapted(subjectClickListener))
            .addDelegate(getSubjectTestUiModelAdapted(subjectTestClickListener))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SubjectUiItem>() {
            override fun areItemsTheSame(
                oldItem: SubjectUiItem,
                newItem: SubjectUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SubjectUiItem,
                newItem: SubjectUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}