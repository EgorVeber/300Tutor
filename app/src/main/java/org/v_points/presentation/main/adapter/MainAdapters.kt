package org.v_points.presentation.main.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ir.mahozad.android.PieChart
import org.v_points.core.UiCoreAttr
import org.v_points.core.UiCoreDrawable
import org.v_points.domain.main.models.ExtraButtonInfoModel
import org.v_points.presentation.main.ui_models.ActivateKeyUiItem
import org.v_points.presentation.main.ui_models.CourseLottieUiItem
import org.v_points.presentation.main.ui_models.CourseProgressUiModel
import org.v_points.presentation.main.ui_models.CourseUiModel
import org.v_points.presentation.main.ui_models.EmptyMainUiItem
import org.v_points.presentation.main.ui_models.FooterUiItem
import org.v_points.presentation.main.ui_models.HeaderUiItem
import org.v_points.presentation.main.ui_models.MainDividerUiItem
import org.v_points.presentation.main.ui_models.MainUiItem
import org.v_points.presentation.main.ui_models.SubjectUiModel
import org.v_points.presentation.main.ui_models.TelegramBindUiItem
import org.v_points.presentation.main.ui_models.TelegramOpenUiItem
import org.v_points.ui_common.util.addPercentSymbol
import org.v_points.ui_common.view_components.getColorAttr
import org.v_points.ui_common.view_components.loadIcon
import org.v_points.ui_common.view_components.setDebouncedClickListener
import org.v_points.ui_common.view_components.setTint
import org.v_points.ui_core.databinding.CourseItemBinding
import org.v_points.ui_core.databinding.CourseProgressItemBinding
import org.v_points.ui_core.databinding.CoursesLotieItemBinding
import org.v_points.ui_core.databinding.EmptyItemBinding
import org.v_points.ui_core.databinding.HomeActivateKeyItemBinding
import org.v_points.ui_core.databinding.MainDividerItemBinding
import org.v_points.ui_core.databinding.MainExtraButtonItemBinding
import org.v_points.ui_core.databinding.SubjectFooterItemBinding
import org.v_points.ui_core.databinding.SubjectHeaderItemBinding
import org.v_points.ui_core.databinding.SubjectItemBinding
import org.v_points.ui_core.databinding.TelegramBindItemBinding
import org.v_points.ui_core.databinding.TelegramOpenItemBinding
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