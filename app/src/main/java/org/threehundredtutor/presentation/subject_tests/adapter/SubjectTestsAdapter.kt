package org.threehundredtutor.presentation.subject_tests.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.databinding.SubjectTestsItemBinding
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.subject_tests.ui_models.SubjectTestsUiItem

object SubjectTestsAdapter {

    fun getSubjectTestUiModelAdapted(subjectTestUiModelClickListener: (SubjectTestUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectTestUiModel, SubjectTestsUiItem, SubjectTestsItemBinding>(
            { layoutInflater, root ->
                SubjectTestsItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.subjectTestLlContainer.setOnClickListener {
                subjectTestUiModelClickListener.invoke(item)
            }
            bind { binding.subjectNameTv.text = item.subjectTestName }
        }
}