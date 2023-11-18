package org.threehundredtutor.presentation.subject.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.databinding.SubjectHeaderItemBinding
import org.threehundredtutor.databinding.SubjectItemBinding
import org.threehundredtutor.databinding.SubjectTestItemBinding
import org.threehundredtutor.presentation.subject.ui_models.SubjectHeaderUiItem
import org.threehundredtutor.presentation.subject.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.subject.ui_models.SubjectUiItem
import org.threehundredtutor.presentation.subject.ui_models.SubjectUiModel

object SubjectAdapters {

    fun getSubjectHeaderUiItemAdapter() =
        adapterDelegateViewBinding<SubjectHeaderUiItem, SubjectUiItem, SubjectHeaderItemBinding>({ layoutInflater, root ->
            SubjectHeaderItemBinding.inflate(layoutInflater, root, false)
        }) {}

    fun getSubjectUiModelAdapted(subjectUiModelClickListener: (SubjectUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectUiModel, SubjectUiItem, SubjectItemBinding>({ layoutInflater, root ->
            SubjectItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.subjectLlContainer.setOnClickListener {
                subjectUiModelClickListener.invoke(item)
            }
            bind { binding.subjectNameTv.text = item.subjectName }
        }

    fun getSubjectTestUiModelAdapted(subjectTestUiModelClickListener: (SubjectTestUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectTestUiModel, SubjectUiItem, SubjectTestItemBinding>({ layoutInflater, root ->
            SubjectTestItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.subjectTestLlContainer.setOnClickListener {
                subjectTestUiModelClickListener.invoke(item)
            }
            bind { binding.subjectTestNameTv.text = item.subjectTestName }
        }
}