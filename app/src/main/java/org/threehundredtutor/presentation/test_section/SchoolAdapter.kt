package org.threehundredtutor.presentation.test_section

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.ui_core.databinding.TestSectionDomainItemBinding

object SchoolAdapter {
    fun getSchoolUiModelAdapted(schoolClickListener: (String, String) -> Unit) =
        adapterDelegateViewBinding<SchoolUiModel, SchoolUiModel, TestSectionDomainItemBinding>(
            { layoutInflater, root ->
                TestSectionDomainItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.root.setOnClickListener {
                if (binding.radioButton.isChecked) return@setOnClickListener
                schoolClickListener.invoke(item.hostUrl, item.name)
            }
            binding.radioButton.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) return@setOnCheckedChangeListener
            }
            bind { payloadList ->
                if (payloadList.isNotEmpty()) {
                    binding.radioButton.isChecked = item.checked
                } else {
                    binding.name.text = item.name
                    binding.domain.text = item.hostUrl
                    binding.radioButton.isChecked = item.checked
                }
            }
        }
}