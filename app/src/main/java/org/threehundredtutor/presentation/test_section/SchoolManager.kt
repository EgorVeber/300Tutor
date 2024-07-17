package org.threehundredtutor.presentation.test_section

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.test_section.SchoolAdapter.getSchoolUiModelAdapted

class SchoolManager(
    schoolClickListener: (String,String) -> Unit,
) : AsyncListDifferDelegationAdapter<SchoolUiModel>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(getSchoolUiModelAdapted(schoolClickListener))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SchoolUiModel>() {
            override fun areItemsTheSame(
                oldItem: SchoolUiModel,
                newItem: SchoolUiModel
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SchoolUiModel,
                newItem: SchoolUiModel
            ): Boolean {
                return oldItem.equals(newItem)
            }

            override fun getChangePayload(oldItem: SchoolUiModel, newItem: SchoolUiModel): Any? {
                return when {
                    oldItem is SchoolUiModel && newItem is SchoolUiModel -> {
                        return oldItem.checked != newItem.checked
                    }

                    else -> null
                }
            }
        }
    }
}