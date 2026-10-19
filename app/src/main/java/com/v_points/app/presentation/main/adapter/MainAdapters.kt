package com.v_points.app.presentation.main.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ir.mahozad.android.PieChart
import com.v_points.app.core.UiCoreAttr
import com.v_points.app.core.UiCoreDrawable
import com.v_points.app.domain.main.models.ExtraButtonInfoModel
import com.v_points.app.presentation.main.ui_models.ActivateKeyUiItem
import com.v_points.app.presentation.main.ui_models.CourseLottieUiItem
import com.v_points.app.presentation.main.ui_models.CourseProgressUiModel
import com.v_points.app.presentation.main.ui_models.CourseUiModel
import com.v_points.app.presentation.main.ui_models.EmptyMainUiItem
import com.v_points.app.presentation.main.ui_models.FooterUiItem
import com.v_points.app.presentation.main.ui_models.HeaderUiItem
import com.v_points.app.presentation.main.ui_models.MainDividerUiItem
import com.v_points.app.presentation.main.ui_models.MainUiItem
import com.v_points.app.presentation.main.ui_models.SubjectUiModel
import com.v_points.app.presentation.main.ui_models.TelegramBindUiItem
import com.v_points.app.presentation.main.ui_models.TelegramOpenUiItem
import com.v_points.app.ui_common.util.addPercentSymbol
import com.v_points.app.ui_common.view_components.getColorAttr
import com.v_points.app.ui_common.view_components.loadIcon
import com.v_points.app.ui_common.view_components.setDebouncedClickListener
import com.v_points.app.ui_common.view_components.setTint
import com.v_points.app.ui_core.databinding.CourseItemBinding
import com.v_points.app.ui_core.databinding.CourseProgressItemBinding
import com.v_points.app.ui_core.databinding.CoursesLotieItemBinding
import com.v_points.app.ui_core.databinding.EmptyItemBinding
import com.v_points.app.ui_core.databinding.HomeActivateKeyItemBinding
import com.v_points.app.ui_core.databinding.MainDividerItemBinding
import com.v_points.app.ui_core.databinding.MainExtraButtonItemBinding
import com.v_points.app.ui_core.databinding.SubjectFooterItemBinding
import com.v_points.app.ui_core.databinding.SubjectHeaderItemBinding
import com.v_points.app.ui_core.databinding.SubjectItemBinding
import com.v_points.app.ui_core.databinding.TelegramBindItemBinding
import com.v_points.app.ui_core.databinding.TelegramOpenItemBinding
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

    fun getActivateKeyUiItemAdapter(
        activateKeyClickListener: () -> Unit, tuckUpClickListener: () -> Unit
    ) =
        adapterDelegateViewBinding<ActivateKeyUiItem, MainUiItem, HomeActivateKeyItemBinding>({ layoutInflater, root ->
            HomeActivateKeyItemBinding.inflate(layoutInflater, root, false)
        }) {

            binding.activateKeyBtn.setOnClickListener {
                activateKeyClickListener.invoke()
            }
            binding.tuckUp.setOnClickListener {
                tuckUpClickListener.invoke()
            }
            bind {
                binding.tuckUp.isVisible = item.enableTuckUp
            }
        }

    fun getSubjectUiModelAdapted(subjectUiModelClickListener: (SubjectUiModel) -> Unit) =
        adapterDelegateViewBinding<SubjectUiModel, MainUiItem, SubjectItemBinding>({ layoutInflater, root ->
            SubjectItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.subjectLlContainer.setOnClickListener {
                subjectUiModelClickListener.invoke(item)
            }
            bind {
                if (item.iconPath.isEmpty()) {
                    binding.iconSubject.setImageResource(UiCoreDrawable.ic_teacher)
                    binding.iconSubject.setTint(UiCoreAttr.primary)
                } else {
                    binding.iconSubject.loadIcon(item.iconPath, UiCoreDrawable.ic_teacher)
                    binding.iconSubject.imageTintList = null
                }
                binding.subjectNameTv.text = item.subjectName
            }
        }

    fun getMainDividerUiItemAdapter() =
        adapterDelegateViewBinding<MainDividerUiItem, MainUiItem, MainDividerItemBinding>({ layoutInflater, root ->
            MainDividerItemBinding.inflate(layoutInflater, root, false)
        }) {
        }

    fun getCourseProgressUiModelAdapter(courseProgressUiModelClickListener: (CourseProgressUiModel) -> Unit) =
        adapterDelegateViewBinding<CourseProgressUiModel, MainUiItem, CourseProgressItemBinding>(
            { layoutInflater, root ->
                CourseProgressItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.courseLlContainer.setOnClickListener {
                courseProgressUiModelClickListener.invoke(item)
            }
            // TODO TutorAndroid-60  PiaChat
            bind {
                binding.courseNameTv.text = item.groupName
                binding.coursePoints.text = item.progressPercents.toString().addPercentSymbol()
                if (item.iconPath.isEmpty()) {
                    binding.iconCourse.setImageResource(UiCoreDrawable.ic_teacher)
                    binding.iconCourse.setTint(UiCoreAttr.primary)
                } else {
                    binding.iconCourse.loadIcon(item.iconPath, UiCoreDrawable.ic_teacher)
                    binding.iconCourse.imageTintList = null
                }

                binding.courseProgress.slices = listOf(
                    PieChart.Slice(
                        fraction = (item.progressPercents / 100.0).toFloat(),
                        color = itemView.getColorAttr(UiCoreAttr.primary, needResId = false)
                    ),
                    PieChart.Slice(
                        fraction = abs(1 - (item.progressPercents / 100.0).toFloat()),
                        color = itemView.getColorAttr(UiCoreAttr.primary40, needResId = false)
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
            bind {
                if (item.iconPath.isEmpty()) {
                    binding.iconCourse.setImageResource(UiCoreDrawable.ic_teacher)
                    binding.iconCourse.setTint(UiCoreAttr.primary)
                } else {
                    binding.iconCourse.loadIcon(item.iconPath, UiCoreDrawable.ic_teacher)
                    binding.iconCourse.imageTintList = null
                }
                binding.courseNameTv.text = item.groupName
            }
        }

    fun getCourseLottieUiItemAdapter(courseLottieUiItemClickListener: () -> Unit) =
        adapterDelegateViewBinding<CourseLottieUiItem, MainUiItem, CoursesLotieItemBinding>({ layoutInflater, root ->
            CoursesLotieItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.pickAppCourses.setDebouncedClickListener {
                courseLottieUiItemClickListener.invoke()
            }
            bind {
                binding.imageView.loadIcon(item.imagePath, UiCoreDrawable.ic_teacher)
            }
        }

    fun getEmptyMainUiItem() =
        adapterDelegateViewBinding<EmptyMainUiItem, MainUiItem, EmptyItemBinding>(
            { layoutInflater, root ->
                EmptyItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                binding.rootTextView.text = item.title
            }
        }

    fun getTelegramBindUiItemAdapter(telegramUiItemClickListener: () -> Unit) =
        adapterDelegateViewBinding<TelegramBindUiItem, MainUiItem, TelegramBindItemBinding>({ layoutInflater, root ->
            TelegramBindItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.root.setOnClickListener {
                telegramUiItemClickListener.invoke()
            }
        }

    fun getTelegramOpenUiItemAdapter(telegramOpenUiItemClickListener: () -> Unit) =
        adapterDelegateViewBinding<TelegramOpenUiItem, MainUiItem, TelegramOpenItemBinding>({ layoutInflater, root ->
            TelegramOpenItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.root.setOnClickListener {
                telegramOpenUiItemClickListener.invoke()
            }
            bind {
                binding.button.text = item.telegramName
            }
        }
}