package com.v_points.app.presentation.subject_detailed.adapter

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.v_points.app.presentation.subject_detailed.ui_models.SubjectDetailedUiItem
import com.v_points.app.presentation.subject_detailed.ui_models.SubjectMenuItemUiModel
import com.v_points.app.ui_core.databinding.SubjectDetailedMenuItemBinding

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