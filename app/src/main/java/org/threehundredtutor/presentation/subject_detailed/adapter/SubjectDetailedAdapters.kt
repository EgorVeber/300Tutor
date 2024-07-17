package org.threehundredtutor.presentation.subject_detailed.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.presentation.subject_detailed.ui_models.SubjectDetailedUiItem
import org.threehundredtutor.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel
import org.threehundredtutor.ui_core.databinding.SubjectDetailedMenuItemBinding

object SubjectDetailedAdapters {
    fun getMenuItemUiModelAdapted(subjectTestUiModelClickListener: (SubjectMenuItemUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectMenuItemUiModel, SubjectDetailedUiItem, SubjectDetailedMenuItemBinding>(
            { layoutInflater, root ->
                SubjectDetailedMenuItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.subjectTestLlContainer.setOnClickListener {
                subjectTestUiModelClickListener.invoke(item)
            }
            bind { binding.subjectNameTv.text = item.text }
        }
}