package org.threehundredtutor.presentation.solution.adapter

import android.content.res.ColorStateList
import android.text.InputFilter
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.R
import org.threehundredtutor.common.applyBackground
import org.threehundredtutor.common.fromHtml
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.common.hideKeyboard
import org.threehundredtutor.common.loadServer
import org.threehundredtutor.common.trimText
import org.threehundredtutor.common.viewcopmponents.bindDividerType
import org.threehundredtutor.common.viewcopmponents.bindResultType
import org.threehundredtutor.databinding.SolutionAnswerSelectRightItemBinding
import org.threehundredtutor.databinding.SolutionAnswerWithErrorsItemBinding
import org.threehundredtutor.databinding.SolutionAnswerWithErrorsResultItemBinding
import org.threehundredtutor.databinding.SolutionCheckButtonItemBinding
import org.threehundredtutor.databinding.SolutionDetailedAnswerItemBinding
import org.threehundredtutor.databinding.SolutionDetailedAnswerResultItemBinding
import org.threehundredtutor.databinding.SolutionDetailedAnswerValidationItemBinding
import org.threehundredtutor.databinding.SolutionDividerItemBinding
import org.threehundredtutor.databinding.SolutionFooterItemBinding
import org.threehundredtutor.databinding.SolutionHeaderItemBinding
import org.threehundredtutor.databinding.SolutionImageItemBinding
import org.threehundredtutor.databinding.SolutionResultButtonItemBinding
import org.threehundredtutor.databinding.SolutionRightAnswerItemBinding
import org.threehundredtutor.databinding.SolutionRightAnswerResultItemBinding
import org.threehundredtutor.databinding.SolutionSelectRightAnswerTitleItemBinding
import org.threehundredtutor.databinding.SolutionSeparatorItemBinding
import org.threehundredtutor.databinding.SolutionSupSubItemBinding
import org.threehundredtutor.databinding.SolutionTextItemBinding
import org.threehundredtutor.databinding.SolutionYoutubeItemBinding
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
                binding.solutionTitle.text = item.questionName
                binding.favoriteImage.imageTintList =
                    ColorStateList.valueOf(
                        itemView.getColorAttr(
                            if (item.isQuestionLikedByStudent) R.attr.defaultRed else R.attr.defaultBlack40,
                            false
                        )
                    )
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
            bind { binding.solutionImage.loadServer(item.idImage) }
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
            binding.checkbox.setOnCheckedChangeListener { _, checked ->
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
                        binding.icon.setImageResource(R.drawable.ic_check)
                        binding.iconContainer.applyBackground(R.attr.defaultGreen)
                    } else {
                        binding.icon.setImageResource(R.drawable.ic_incorrect_answer)
                        binding.iconContainer.applyBackground(R.attr.warning)
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
                    binding.answerEditText.filters = null
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
            binding.checkButton.setOnClickListener { view ->
                val answer = binding.answerEditText.trimText()
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
                binding.resultButton.bindResultType(item.type) // можно кастыль пока сервак неправильно присылает
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