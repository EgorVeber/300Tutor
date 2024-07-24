package org.threehundredtutor.presentation.test_section

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.test_section.SchoolAdapter.getErrorSchoolUiItem
import org.threehundredtutor.presentation.test_section.SchoolAdapter.getSchoolUiModelAdapted

class SchoolManager(
    schoolClickListener: (String, String) -> Unit,
) : AsyncListDifferDelegationAdapter<SchoolUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager.addDelegate(getSchoolUiModelAdapted(schoolClickListener))
        delegatesManager.addDelegate(getErrorSchoolUiItem())
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SchoolUiItem>() {
            override fun areItemsTheSame(
                oldItem: SchoolUiItem,
                newItem: SchoolUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SchoolUiItem,
                newItem: SchoolUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }

            override fun getChangePayload(oldItem: SchoolUiItem, newItem: SchoolUiItem): Any? {
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