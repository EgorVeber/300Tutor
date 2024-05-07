package org.threehundredtutor.presentation.solution.adapter

import android.text.InputFilter
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.core.UiCoreDrawable
import org.threehundredtutor.presentation.solution.solution_factory.GravityAlign.Companion.getGravity
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.DividerUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.FooterUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.ImageUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.ResultButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.SeparatorUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.SupSubUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.TextUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.YoutubeUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.right_answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.AnswerSelectRightUiModel
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerCheckButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerTitleUiItem
import org.threehundredtutor.ui_common.util.fromHtml
import org.threehundredtutor.ui_common.view_components.applyBackground
import org.threehundredtutor.ui_common.view_components.hideKeyboard
import org.threehundredtutor.ui_common.view_components.loadImageMedium
import org.threehundredtutor.ui_common.view_components.setDebouncedCheckedChangeListener
import org.threehundredtutor.ui_common.view_components.setTint
import org.threehundredtutor.ui_common.view_components.trimText
import org.threehundredtutor.ui_core.databinding.SolutionAnswerSelectRightItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionAnswerWithErrorsItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionAnswerWithErrorsResultItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionCheckButtonItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDetailedAnswerItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDetailedAnswerResultItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDetailedAnswerValidationItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDividerItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionFooterItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionHeaderItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionImageItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionResultButtonItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionRightAnswerItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionRightAnswerResultItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionSelectRightAnswerTitleItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionSeparatorItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionSupSubItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionTextItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionYoutubeItemBinding

object SolutionAdapters {

    /** Общие адаптеры для построения всех типом вопросов.*/
    fun getHeaderUiItemAdapter(questionLikeClickListener: (HeaderUiItem) -> Unit) =
        adapterDelegateViewBinding<HeaderUiItem, SolutionUiItem, SolutionHeaderItemBinding>({ layoutInflater, root ->
            SolutionHeaderItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.favoriteImage.setOnClickListener {
                questionLikeClickListener.invoke(item)
            }
            bind {
                binding.solutionTitle.text = item.questionName.trim()
                if (item.isQuestionLikedByStudent) {
                    binding.favoriteImage.setTint(UiCoreAttr.defaultRed)
                } else {
                    binding.favoriteImage.setTint(UiCoreAttr.defaultBlack40)
                }
            }
        }

    fun getFooterUiItemAdapter() =
        adapterDelegateViewBinding<FooterUiItem, SolutionUiItem, SolutionFooterItemBinding>({ layoutInflater, root ->
            SolutionFooterItemBinding.inflate(layoutInflater, root, false)
        }) {}

    fun getTextUiItemAdapter() =
        adapterDelegateViewBinding<TextUiItem, SolutionUiItem, SolutionTextItemBinding>({ layoutInflater, root ->
            SolutionTextItemBinding.inflate(layoutInflater, root, false)
        }) {
            bind {
                binding.textItem.text = item.text.fromHtml()
                binding.textItem.gravity = item.gravityAlign.getGravity()
            }
        }

    fun getImageUiItemAdapter(imageClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<ImageUiItem, SolutionUiItem, SolutionImageItemBinding>({ layoutInflater, root ->
            SolutionImageItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.solutionImage.setOnClickListener { imageClickListener.invoke(item.idImage) }
            bind { binding.solutionImage.loadImageMedium(item.idImage, item.staticUrl) }
        }

    fun getYoutubeUiItemAdapter(youtubeClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<YoutubeUiItem, SolutionUiItem, SolutionYoutubeItemBinding>({ layoutInflater, root ->
            SolutionYoutubeItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.openYoutube.setOnClickListener { youtubeClickListener.invoke(item.link) }
        }

    fun getSupSubUiItemAdapter() =
        adapterDelegateViewBinding<SupSubUiItem, SolutionUiItem, SolutionSupSubItemBinding>({ layoutInflater, root ->
            SolutionSupSubItemBinding.inflate(layoutInflater, root, false)
        }) {
            bind {
                binding.textItem.text = item.text.fromHtml()
                binding.textItem.gravity = item.gravityAlign.getGravity()
            }
        }

    fun getDividerUiItemAdapter() =
        adapterDelegateViewBinding<DividerUiItem, SolutionUiItem, SolutionDividerItemBinding>({ layoutInflater, root ->
            SolutionDividerItemBinding.inflate(layoutInflater, root, false)
        }) {
            bind { binding.viewDivider.bindDividerType(item.dividerType) }
        }

    fun getSeparatorUiItemAdapter() =
        adapterDelegateViewBinding<SeparatorUiItem, SolutionUiItem, SolutionSeparatorItemBinding>({ layoutInflater, root ->
            SolutionSeparatorItemBinding.inflate(layoutInflater, root, false)
        }) {}

    fun getResultButtonUiItemAdapter() =
        adapterDelegateViewBinding<ResultButtonUiItem, SolutionUiItem, SolutionResultButtonItemBinding>(
            { layoutInflater, root ->
                SolutionResultButtonItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                binding.pointQuestionTv.text = item.pointString
                binding.pointQuestionTv.isVisible = item.pointString.isNotEmpty()
                binding.answerResultButton.bindResultType(item.answerValidationResultType)
            }
        }

    /** Адаптеры для постоения интерфейса вопроса с типом SelectRightAnswerOrAnswers.*/
    fun getSelectRightAnswerTitleUiItemAdapter() =
        adapterDelegateViewBinding<SelectRightAnswerTitleUiItem, SolutionUiItem, SolutionSelectRightAnswerTitleItemBinding>(
            { layoutInflater, root ->
                SolutionSelectRightAnswerTitleItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind { binding.title.text = item.title }
        }

    fun getAnswerSelectRightUiModelAdapter(selectRightAnswerClickListener: (String, String, Boolean) -> Unit) =
        adapterDelegateViewBinding<AnswerSelectRightUiModel, SolutionUiItem, SolutionAnswerSelectRightItemBinding>(
            { layoutInflater, root ->
                SolutionAnswerSelectRightItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.checkbox.setDebouncedCheckedChangeListener { checked ->
                selectRightAnswerClickListener.invoke(item.questionId, item.answer, checked)
            }
            bind { payloadList ->
                if (payloadList.isEmpty()) {
                    // TODO попытаться опимизировать есть полдлаги После добавления теста. TutorAndroid-31
                    binding.checkbox.text = item.answer
                    binding.checkbox.isChecked = item.checked
                    binding.checkbox.isEnabled = item.enabled
                    binding.iconContainer.isVisible = !item.enabled
                    if (item.enabled) return@bind
                    if (item.rightAnswer) {
                        binding.icon.setImageResource(UiCoreDrawable.ic_check)
                        binding.iconContainer.applyBackground(UiCoreAttr.defaultGreen)
                    } else {
                        binding.icon.setImageResource(UiCoreDrawable.ic_incorrect_answer)
                        binding.iconContainer.applyBackground(UiCoreAttr.warning)
                    }
                } else {
                    binding.checkbox.text = item.answer
                }
            }
        }

    fun getSelectRightAnswerCheckButtonUiItemAdapter(selectRightAnswerCheckButtonClickListener: (String) -> Unit) =
        adapterDelegateViewBinding<SelectRightAnswerCheckButtonUiItem, SolutionUiItem, SolutionCheckButtonItemBinding>(
            { layoutInflater, root ->
                SolutionCheckButtonItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.checkButton.setOnClickListener {
                selectRightAnswerCheckButtonClickListener.invoke(
                    item.questionId
                )
            }
        }

    /** Адаптеры для постоения интерфейса вопроса с типом RightAnswer.*/
    fun getRightAnswerUiModelAdapter(rightAnswerClickListener: (RightAnswerUiModel, String) -> Unit) =
        adapterDelegateViewBinding<RightAnswerUiModel, SolutionUiItem, SolutionRightAnswerItemBinding>(
            { layoutInflater, root ->
                SolutionRightAnswerItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.checkButton.setOnClickListener { view ->
                val answer = binding.answerEditText.text.toString().trim()
                if (answer.isNotEmpty()) {
                    rightAnswerClickListener.invoke(item, answer)
                    view.hideKeyboard()
                }
            }
            bind {
                if (item.caseInSensitive) {
                    binding.answerEditText.filters = arrayOf<InputFilter>(InputFilter.AllCaps())
                } else {
                    binding.answerEditText.filters = arrayOf<InputFilter>()
                }
            }
        }

    fun getRightAnswerResultUiItemAdapter() =
        adapterDelegateViewBinding<RightAnswerResultUiItem, SolutionUiItem, SolutionRightAnswerResultItemBinding>(
            { layoutInflater, root ->
                SolutionRightAnswerResultItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                binding.pointQuestionTv.text = item.pointsString
                binding.pointQuestionTv.isVisible = item.pointsString.isNotEmpty()
                binding.correctAnswerValue.text = item.rightAnswer
                binding.yourAnswerValue.text = item.answer
                binding.answerResultButton.bindResultType(item.answerValidationResultType)
            }
        }

    /** Адаптер для постоения интерфейса вопроса с типом AnswerWithErrors.*/
    fun getAnswerWithErrorsUiModelAdapter(answerWithErrorsClickListener: (AnswerWithErrorsUiModel, String) -> Unit) =
        adapterDelegateViewBinding<AnswerWithErrorsUiModel, SolutionUiItem, SolutionAnswerWithErrorsItemBinding>(
            { layoutInflater, root ->
                SolutionAnswerWithErrorsItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.checkButtonAwE.setOnClickListener { view ->
                val answer = binding.answerEditTextAwe.trimText()
                if (answer.isNotEmpty()) {
                    answerWithErrorsClickListener.invoke(item, answer)
                    view.hideKeyboard()
                }
            }
        }

    fun getAnswerWithErrorsResultUiItemAdapter() =
        adapterDelegateViewBinding<AnswerWithErrorsResultUiItem, SolutionUiItem, SolutionAnswerWithErrorsResultItemBinding>(
            { layoutInflater, root ->
                SolutionAnswerWithErrorsResultItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                binding.pointQuestionTv.text = item.pointString
                binding.pointQuestionTv.isVisible = item.pointString.isNotEmpty()
                binding.correctAnswerValue.text = item.rightAnswer
                binding.yourAnswerValue.text = item.answer
                binding.answerResultButton.bindResultType(item.answerValidationResultType)
            }
        }

    /** Адаптер для постоения интерфейса вопроса с типом Detailed.*/
    fun getDetailedAnswerUiItemAdapter(
        detailedAnswerClickListener: (DetailedAnswerUiItem, String) -> Unit,
        detailedAnswerTextChangedListener: (DetailedAnswerUiItem, String) -> Unit
    ) =
        adapterDelegateViewBinding<DetailedAnswerUiItem, SolutionUiItem, SolutionDetailedAnswerItemBinding>(
            { layoutInflater, root ->
                SolutionDetailedAnswerItemBinding.inflate(
                    layoutInflater, root, false
                )
            })
        {
            binding.checkButton.setOnClickListener { view ->
                val answer = binding.answerEditText.trimText()
                if (answer.isNotEmpty()) {
                    detailedAnswerClickListener.invoke(item, answer)
                    view.hideKeyboard()
                }
            }
            // TODO Баг с сохранение теста во все модели детального ответа.
            binding.answerEditText.addTextChangedListener {
                detailedAnswerTextChangedListener.invoke(item, it.toString())
            }
            bind { payloadList ->
                if (payloadList.isEmpty()) {
                    binding.answerEditText.setText(item.inputAnswer)
                }
            }
        }

    fun getDetailedAnswerResultUiItemAdapter() =
        adapterDelegateViewBinding<DetailedAnswerResultUiItem, SolutionUiItem, SolutionDetailedAnswerResultItemBinding>(
            { layoutInflater, root ->
                SolutionDetailedAnswerResultItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind { binding.yourAnswerValue.text = item.answer }
        }

    fun getDetailedAnswerValidationUiItemAdapted(
        detailedAnswerValidationClickListener: (DetailedAnswerValidationUiItem, String) -> Unit,
        deleteValidationClickListener: (DetailedAnswerValidationUiItem) -> Unit
    ) =
        adapterDelegateViewBinding<DetailedAnswerValidationUiItem, SolutionUiItem, SolutionDetailedAnswerValidationItemBinding>(
            { layoutInflater, root ->
                SolutionDetailedAnswerValidationItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.estimateButton.setOnClickListener { view ->
                if (!item.isValidated) {
                    val inputPoint = binding.inputPointEditText.text.toString().trim()
                    if (inputPoint.isNotEmpty()) {
                        detailedAnswerValidationClickListener.invoke(item, inputPoint)
                        view.hideKeyboard()
                    }
                }
            }
            binding.iconDelete.setOnClickListener { deleteValidationClickListener.invoke(item) }
            bind {
                // binding.pointQuestionTv.text = item.pointsString
                // binding.pointQuestionTv.isVisible = item.pointsString.isNotEmpty()
                binding.totalPointEditText.setText(item.pointTotal)
                binding.iconDelete.isVisible = item.isValidated
                binding.resultButton.bindResultType(item.type)
                binding.resultButton.isVisible = item.isValidated
                binding.estimateButton.isClickable = !item.isValidated
                binding.estimateButton.isVisible = !item.isValidated
                binding.needToCheckButton.isVisible = !item.isValidated
                binding.inputPointEditText.isEnabled = !item.isValidated
                if (item.inputPoint.isNotEmpty()) {
                    binding.inputPointEditText.setText(item.inputPoint)
                } else {
                    binding.inputPointEditText.text = null
                }
            }
        }
}