package org.threehundredtutor.presentation.main.adapter

import android.text.InputFilter
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ir.mahozad.android.PieChart
import org.threehundredtutor.R
import org.threehundredtutor.common.addPercent
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.common.hideKeyboard
import org.threehundredtutor.databinding.CourseItemBinding
import org.threehundredtutor.databinding.CourseProgressItemBinding
import org.threehundredtutor.databinding.CoursesLotieItemBinding
import org.threehundredtutor.databinding.FooterContentItemBinding
import org.threehundredtutor.databinding.HeaderContentItemBinding
import org.threehundredtutor.databinding.HomeActivateKeyItemBinding
import org.threehundredtutor.databinding.MainExtraButtonItemBinding
import org.threehundredtutor.databinding.SubjectFooterItemBinding
import org.threehundredtutor.databinding.SubjectHeaderItemBinding
import org.threehundredtutor.databinding.SubjectItemBinding
import org.threehundredtutor.databinding.SubjectTestItemBinding
import org.threehundredtutor.domain.main.models.ExtraButtonInfoModel
import org.threehundredtutor.presentation.main.ui_models.ActivateKeyUiItem
import org.threehundredtutor.presentation.main.ui_models.CourseLottieUiItem
import org.threehundredtutor.presentation.main.ui_models.CourseProgressUiModel
import org.threehundredtutor.presentation.main.ui_models.CourseUiModel
import org.threehundredtutor.presentation.main.ui_models.FooterContentUiItem
import org.threehundredtutor.presentation.main.ui_models.HeaderContentUiItem
import org.threehundredtutor.presentation.main.ui_models.HeaderUiItem
import org.threehundredtutor.presentation.main.ui_models.FooterUiItem
import org.threehundredtutor.presentation.main.ui_models.SubjectTestUiModel
import org.threehundredtutor.presentation.main.ui_models.MainUiItem
import org.threehundredtutor.presentation.main.ui_models.SubjectUiModel
import kotlin.math.abs

object MainAdapters {

    fun getSubjectHeaderUiItemAdapter() =
        adapterDelegateViewBinding<HeaderUiItem, MainUiItem, SubjectHeaderItemBinding>({ layoutInflater, root ->
            SubjectHeaderItemBinding.inflate(layoutInflater, root, false)
        }) {
            bind {
                binding.solutionTitle.text = item.title
            }
        }

    fun getMainHeaderContentUiItemAdapter() =
        adapterDelegateViewBinding<HeaderContentUiItem, MainUiItem, HeaderContentItemBinding>({ layoutInflater, root ->
            HeaderContentItemBinding.inflate(layoutInflater, root, false)
        }) {}

    fun getFooterContentUiItemAdapter() =
        adapterDelegateViewBinding<FooterContentUiItem, MainUiItem, FooterContentItemBinding>({ layoutInflater, root ->
            FooterContentItemBinding.inflate(layoutInflater, root, false)
        }) {}

    fun getFooterUiItemAdapter() =
        adapterDelegateViewBinding<FooterUiItem, MainUiItem, SubjectFooterItemBinding>({ layoutInflater, root ->
            SubjectFooterItemBinding.inflate(layoutInflater, root, false)
        }) {}

    fun getExtraButtonUiItemAdapter(extraButtonClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<ExtraButtonInfoModel, MainUiItem, MainExtraButtonItemBinding>(
            { layoutInflater, root ->
                MainExtraButtonItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.extraButton.setOnClickListener {
                extraButtonClickListener.invoke(item.link)
            }
            bind {
                binding.extraButton.text = item.text
            }
        }

    fun getActivateKeyUiItemAdapter(activateKeyClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<ActivateKeyUiItem, MainUiItem, HomeActivateKeyItemBinding>({ layoutInflater, root ->
            HomeActivateKeyItemBinding.inflate(layoutInflater, root, false)
        }) {

            binding.activateKeyBtn.setOnClickListener {
                val key = binding.activateKetEdt.text.toString().trim()
                if (key.isNotEmpty()) {
                    activateKeyClickListener.invoke(key)
                    it.hideKeyboard()
                }
            }

            binding.activateKetEdt.setOnEditorActionListener { textView, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    || actionId == EditorInfo.IME_ACTION_DONE
                    || event.action == KeyEvent.ACTION_DOWN
                    && event.keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    val key = binding.activateKetEdt.text.toString().trim()
                    if (key.isNotEmpty()) {
                        activateKeyClickListener.invoke(textView.toString())
                        false
                    } else {
                        true
                    }
                } else {
                    true
                }
            }
            binding.activateKetEdt.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
                source.toString().filterNot { it.isWhitespace() }
            })
        }

    fun getSubjectUiModelAdapted(subjectUiModelClickListener: (SubjectUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectUiModel, MainUiItem, SubjectItemBinding>({ layoutInflater, root ->
            SubjectItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.subjectLlContainer.setOnClickListener {
                subjectUiModelClickListener.invoke(item)
            }
            bind { binding.subjectNameTv.text = item.subjectName }
        }

    fun getCourseProgressUiModelAdapter(courseProgressUiModelClickListener: (CourseProgressUiModel) -> Unit) =
        adapterDelegateViewBinding<CourseProgressUiModel, MainUiItem, CourseProgressItemBinding>(
            { layoutInflater, root ->
                CourseProgressItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.courseLlContainer.setOnClickListener {
                courseProgressUiModelClickListener.invoke(item)
            }
            bind {
                binding.courseNameTv.text = item.groupName
                binding.coursePoints.text = item.progressPercents.toString().addPercent()
                binding.courseProgress.slices = listOf(
                    PieChart.Slice(
                        fraction = (item.progressPercents / 100.0).toFloat(),
                        color = itemView.getColorAttr(R.attr.primary, needResId = false)
                    ),
                    PieChart.Slice(
                        fraction = abs(1 - (item.progressPercents / 100.0).toFloat()),
                        color = itemView.getColorAttr(R.attr.primary40, needResId = false)
                    )
                )
            }
        }

    fun getCourseUiModelAdapter(courseUiModelClickListener: (CourseUiModel) -> Unit) =
        adapterDelegateViewBinding<CourseUiModel, MainUiItem, CourseItemBinding>({ layoutInflater, root ->
            CourseItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.courseLlContainer.setOnClickListener {
                courseUiModelClickListener.invoke(item)
            }
            bind { binding.courseNameTv.text = item.groupName }
        }

    fun getSubjectTestUiModelAdapted(subjectTestUiModelClickListener: (SubjectTestUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectTestUiModel, MainUiItem, SubjectTestItemBinding>({ layoutInflater, root ->
            SubjectTestItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.subjectTestLlContainer.setOnClickListener {
                subjectTestUiModelClickListener.invoke(item)
            }
            bind { binding.subjectTestNameTv.text = item.subjectTestName }
        }

    fun getCourseLottieUiItemAdapter(courseLottieUiItemClickListener: () -> Unit) =
        adapterDelegateViewBinding<CourseLottieUiItem, MainUiItem, CoursesLotieItemBinding>({ layoutInflater, root ->
            CoursesLotieItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.chooseButton.setOnClickListener {
                courseLottieUiItemClickListener.invoke()
            }
        }
}