package org.threehundredtutor.presentation.solution.adapter

import android.text.InputFilter
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.core.UiCoreAttr
import org.threehundredtutor.core.UiCoreDrawable
import org.threehundredtutor.presentation.favorites.AnswerFavoritesWithErrorsResultUiItem
import org.threehundredtutor.presentation.favorites.EmptyUiItem
import org.threehundredtutor.presentation.favorites.SuccessAnswerTitleUiItem
import org.threehundredtutor.presentation.solution.solution_factory.GravityAlign.Companion.getGravity
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsResultUiItem
import org.threehundredtutor.presentation.solution.ui_models.answer_erros.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerInputUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerValidationUiItem
import org.threehundredtutor.presentation.solution.ui_models.detailed_answer.DetailedAnswerYourAnswerUiItem
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
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerCheckButtonUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerTitleUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel
import org.threehundredtutor.ui_common.util.fromHtml
import org.threehundredtutor.ui_common.view_components.hideKeyboard
import org.threehundredtutor.ui_common.view_components.loadImageMedium
import org.threehundredtutor.ui_common.view_components.setDebouncedCheckedChangeListener
import org.threehundredtutor.ui_common.view_components.setTint
import org.threehundredtutor.ui_common.view_components.trimText
import org.threehundredtutor.ui_core.databinding.EmptyItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionAnswerSelectRightItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionAnswerWithErrorsItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionAnswerWithErrorsResultItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionCheckButtonItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDetailedAnswerItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDetailedAnswerResultItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDetailedAnswerValidationItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionDividerItemBinding
import org.threehundredtutor.ui_core.databinding.SolutionFavoritesAnswerWithErrorsResultItemBinding
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
import org.threehundredtutor.ui_core.databinding.SuccessAnswerTitleItemBinding

object SolutionAdapters {

    /** Общие адаптеры для построения всех типом вопросов.*/
    fun getHeaderUiItemAdapter(questionLikeClickListener: (HeaderUiItem) -> Unit) =
        adapterDelegateViewBinding<HeaderUiItem, SolutionUiItem, SolutionHeaderItemBinding>({ layoutInflater, root ->
            SolutionHeaderItemBinding.inflate(layoutInflater, root, false)
        }) {
            binding.favoriteImage.setOnClickListener {
                questionLikeClickListener.invoke(item)
            }

            fun bindLikeQuestionIcon() {
                if (item.isQuestionLikedByStudent) {
                    binding.favoriteImage.setTint(UiCoreAttr.defaultRed)
                } else {
                    binding.favoriteImage.setTint(UiCoreAttr.defaultBlack40)
                }
            }

            bind { payloadList ->
                if (payloadList.isEmpty()) {
                    binding.solutionTitle.text = item.questionNumber.trim()
                    bindLikeQuestionIcon()
                } else {
                    bindLikeQuestionIcon()
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
            bind {
                binding.solutionImage.layout(0, 0, 0, 0) //TODO еще раз проверить все с фотками
                binding.solutionImage.loadImageMedium(item.path)
            }
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
            bind {
                binding.viewDivider.bindDividerType(item.dividerType)
            }
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
        adapterDelegateViewBinding<SelectRightAnswerUiModel, SolutionUiItem, SolutionAnswerSelectRightItemBinding>(
            { layoutInflater, root ->
                SolutionAnswerSelectRightItemBinding.inflate(layoutInflater, root, false)
            }) {

            binding.checkbox.setDebouncedCheckedChangeListener { view, checked ->
                if (view.isPressed) {
                    selectRightAnswerClickListener.invoke(item.questionId, item.answer, checked)
                }
            }

            fun bindAnswerTextAndChecked() {
                binding.checkbox.text = item.answer
                binding.checkbox.isChecked = item.checked
            }

            fun bindValidated() {
                binding.checkbox.isEnabled = !item.isValidated
                binding.iconContainer.isVisible = item.isValidated
            }

            fun bindAnswerIcon() {
                if (!item.isValidated) return
                if (item.rightAnswer) {
                    binding.icon.setImageResource(UiCoreDrawable.ic_check)
                    binding.iconContainer.setBackgroundResource(UiCoreDrawable.rectangle_full_green_background)
                } else {
                    binding.icon.setImageResource(UiCoreDrawable.ic_incorrect_answer)
                    binding.iconContainer.setBackgroundResource(UiCoreDrawable.rectangle_full_warning_background)
                }
            }

            bind { payloadList ->
                when (payloadList.lastOrNull()) {
                    is SolutionManager.Companion.AnswerSelectRightPayload.Checked -> {}

                    is SolutionManager.Companion.AnswerSelectRightPayload.Validated -> {
                        bindValidated()
                        bindAnswerIcon()
                    }

                    else -> {
                        bindAnswerTextAndChecked()
                        bindValidated()
                        bindAnswerIcon()
                    }
                }
            }
        }

    fun getSelectRightAnswerCheckButtonUiItemAdapter(selectRightAnswerCheckButtonClickListener: (SelectRightAnswerCheckButtonUiItem) -> Unit) =
        adapterDelegateViewBinding<SelectRightAnswerCheckButtonUiItem, SolutionUiItem, SolutionCheckButtonItemBinding>(
            { layoutInflater, root ->
                SolutionCheckButtonItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.checkButton.setOnClickListener {
                selectRightAnswerCheckButtonClickListener.invoke(item)
            }
        }

    /** Адаптеры для постоения интерфейса вопроса с типом RightAnswer.*/
    fun getRightAnswerUiModelAdapter(
        rightAnswerClickListener: (RightAnswerUiModel, String) -> Unit,
        rightAnswerTextChangedListener: (String, String) -> Unit
    ) =
        adapterDelegateViewBinding<RightAnswerUiModel, SolutionUiItem, SolutionRightAnswerItemBinding>(
            { layoutInflater, root ->
                SolutionRightAnswerItemBinding.inflate(layoutInflater, root, false)
            }) {

            binding.answerEditText.doAfterTextChanged {
                rightAnswerTextChangedListener.invoke(item.questionId, it.toString())
            }

            binding.checkButton.setOnClickListener { view ->
                val answer = binding.answerEditText.text.toString().trim()
                if (answer.isNotEmpty()) {
                    rightAnswerClickListener.invoke(item, answer)
                    view.hideKeyboard()
                }
            }
            bind { payloadList ->
                if (payloadList.isEmpty()) binding.answerEditText.setText(item.inputAnswer)
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
    fun getAnswerWithErrorsUiModelAdapter(
        answerWithErrorsTextChangedListener: (String, String) -> Unit,
        answerWithErrorsClickListener: (AnswerWithErrorsUiModel, String) -> Unit
    ) =
        adapterDelegateViewBinding<AnswerWithErrorsUiModel, SolutionUiItem, SolutionAnswerWithErrorsItemBinding>(
            { layoutInflater, root ->
                SolutionAnswerWithErrorsItemBinding.inflate(layoutInflater, root, false)
            }) {
            binding.answerEditTextAwe.doAfterTextChanged {
                answerWithErrorsTextChangedListener.invoke(item.questionId, it.toString())
            }
            binding.checkButtonAwE.setOnClickListener { view ->
                val answer = binding.answerEditTextAwe.trimText()
                if (answer.isNotEmpty()) {
                    answerWithErrorsClickListener.invoke(item, answer)
                    view.hideKeyboard()
                }
            }
            bind { payloadList ->
                if (payloadList.isEmpty()) binding.answerEditTextAwe.setText(item.inputAnswer)
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
        detailedAnswerClickListener: (DetailedAnswerInputUiItem, String) -> Unit,
        detailedAnswerTextChangedListener: (String, String) -> Unit
    ) =
        adapterDelegateViewBinding<DetailedAnswerInputUiItem, SolutionUiItem, SolutionDetailedAnswerItemBinding>(
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
            binding.answerEditText.doAfterTextChanged {
                detailedAnswerTextChangedListener.invoke(item.questionId, it.toString())
            }
            bind { payloadList ->
                if (payloadList.isEmpty()) {
                    binding.answerEditText.setText(item.inputAnswer)
                }
            }
        }

    fun getDetailedAnswerResultUiItemAdapter() =
        adapterDelegateViewBinding<DetailedAnswerYourAnswerUiItem, SolutionUiItem, SolutionDetailedAnswerResultItemBinding>(
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

            fun bindInputPoint() {
                binding.inputPointEditText.setText(item.inputPoint)
                binding.totalPointEditText.setText(item.pointTotal)
                binding.pointQuestionTv.text = item.pointsString
            }

            fun bindVisible() {
                binding.iconDelete.isVisible = item.isValidated
                binding.pointQuestionTv.isVisible = item.isValidated
                binding.estimateButton.isVisible = !item.isValidated
                binding.inputPointTextInputLayout.isVisible = !item.isValidated
                binding.totalPointTextInputLayout.isVisible = !item.isValidated
            }

            bind { payloadList ->
                if (payloadList.isEmpty()) {
                    binding.resultButton.bindResultType(item.type)
                    bindVisible()
                    bindInputPoint()
                } else {
                    binding.resultButton.bindResultType(item.type)
                    bindVisible()
                    bindInputPoint()
                }
            }
        }


    /** Адаптеры для постоения Избранных воропсов SelectRightAnswerOrAnswers.*/
    fun getSuccessAnswerTitleUiItemAdapter() =
        adapterDelegateViewBinding<SuccessAnswerTitleUiItem, SolutionUiItem, SuccessAnswerTitleItemBinding>(
            { layoutInflater, root ->
                SuccessAnswerTitleItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                binding.title.text = item.title
            }
        }


    /** Адаптер для постоения интерфейса вопроса с типом AnswerWithErrors Favorites.*/
    fun getFavoritesAnswerWithErrorsResultUiItemAdapter() =
        adapterDelegateViewBinding<AnswerFavoritesWithErrorsResultUiItem, SolutionUiItem, SolutionFavoritesAnswerWithErrorsResultItemBinding>(
            { layoutInflater, root ->
                SolutionFavoritesAnswerWithErrorsResultItemBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }) {
            bind {
                binding.correctAnswerValue.text = item.answer
                binding.correctAnswerTitle.text = item.title
            }
        }

    fun getAnswerFavoritesSelectRightUiModelAdapter() =
        adapterDelegateViewBinding<SelectRightAnswerUiModel, SolutionUiItem, SolutionAnswerSelectRightItemBinding>(
            { layoutInflater, root ->
                SolutionAnswerSelectRightItemBinding.inflate(layoutInflater, root, false)
            }) {

            fun bindAnswerTextAndChecked() {
                binding.checkbox.text = item.answer
                binding.checkbox.isChecked = item.checked
            }

            fun bindValidated() {
                binding.checkbox.isEnabled = !item.isValidated
                binding.iconContainer.isVisible = item.isValidated
            }

            fun bindAnswerIcon() {
                if (!item.isValidated) return
                if (item.rightAnswer) {
                    binding.icon.setImageResource(UiCoreDrawable.ic_check)
                    binding.iconContainer.setBackgroundResource(UiCoreDrawable.rectangle_full_green_background)
                } else {
                    binding.icon.setImageResource(UiCoreDrawable.ic_incorrect_answer)
                    binding.iconContainer.setBackgroundResource(UiCoreDrawable.rectangle_full_warning_background)
                }
            }

            bind {
                bindValidated()
                bindAnswerTextAndChecked()
                bindAnswerIcon()
            }
        }



    fun getEmptyUiItem() =
        adapterDelegateViewBinding<EmptyUiItem, SolutionUiItem, EmptyItemBinding>(
            { layoutInflater, root ->
                EmptyItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                binding.rootTextView.text = item.title
            }
        }
}