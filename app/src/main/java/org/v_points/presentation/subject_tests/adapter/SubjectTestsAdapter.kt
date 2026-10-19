package org.v_points.presentation.subject_tests.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.v_points.presentation.subject_tests.ui_models.SubjectEmptyTestsUiItem
import org.v_points.presentation.subject_tests.ui_models.SubjectTestUiModel
import org.v_points.presentation.subject_tests.ui_models.SubjectTestsUiItem
import org.v_points.ui_core.databinding.SubjectTestsEmptyItemBinding
import org.v_points.ui_core.databinding.SubjectTestsItemBinding

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

    fun getSubjectEmptyTestsAdapted() =
        adapterDelegateViewBinding<SubjectEmptyTestsUiItem, SubjectTestsUiItem, SubjectTestsEmptyItemBinding>(
            { layoutInflater, root ->
                SubjectTestsEmptyItemBinding.inflate(layoutInflater, root, false)
            }) {
        }
}