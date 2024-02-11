package org.threehundredtutor.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getActivateKeyUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getCourseLottieUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getCourseProgressUiModelAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getCourseUiModelAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getExtraButtonUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getFooterContentUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getFooterUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getMainHeaderContentUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getSubjectHeaderUiItemAdapter
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getSubjectTestUiModelAdapted
import org.threehundredtutor.presentation.main.adapter.MainAdapters.getSubjectUiModelAdapted
import org.threehundredtutor.presentation.main.ui_models.CourseProgressUiModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel
import org.threehundredtutor.presentation.main.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.main.ui_models.MainUiItem
import org.threehundredtutor.presentation.main.ui_models.SubjectUiModel

class MainManager(
    subjectClickListener: (SubjectUiModel) -> Unit,
    subjectTestClickListener: (SubjectTestUiModel) -> Unit,
    activateKeyClickListener: (String) -> Unit,
    courseProgressUiModelClickListener: (CourseProgressUiModel) -> Unit,
    courseUiModelClickListener: (CourseUiModel) -> Unit,
    extraButtonClickListener: (String) -> Unit,
    courseLottieUiItemClickListener: () -> Unit
) : AsyncListDifferDelegationAdapter<MainUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getSubjectHeaderUiItemAdapter())
            .addDelegate(getFooterUiItemAdapter())
            .addDelegate(getActivateKeyUiItemAdapter(activateKeyClickListener))
            .addDelegate(getSubjectUiModelAdapted(subjectClickListener))
            .addDelegate(getSubjectTestUiModelAdapted(subjectTestClickListener))
            .addDelegate(getCourseProgressUiModelAdapter(courseProgressUiModelClickListener))
            .addDelegate(getCourseUiModelAdapter(courseUiModelClickListener))
            .addDelegate(getFooterContentUiItemAdapter())
            .addDelegate(getMainHeaderContentUiItemAdapter())
            .addDelegate(getExtraButtonUiItemAdapter(extraButtonClickListener))
            .addDelegate(getCourseLottieUiItemAdapter(courseLottieUiItemClickListener))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MainUiItem>() {
            override fun areItemsTheSame(
                oldItem: MainUiItem,
                newItem: MainUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: MainUiItem,
                newItem: MainUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }
        }
    }
}