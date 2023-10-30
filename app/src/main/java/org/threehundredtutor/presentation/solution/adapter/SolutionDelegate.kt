package org.threehundredtutor.presentation.solution.adapter

import android.text.InputFilter
import android.text.InputFilter.AllCaps
import androidx.core.view.isVisible
import com.google.android.material.button.MaterialButton
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.R
import org.threehundredtutor.common.applyBackground
import org.threehundredtutor.common.fromHtml
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.common.hideKeyboard
import org.threehundredtutor.common.loadServer
import org.threehundredtutor.databinding.AnswerButtonItemBinding
import org.threehundredtutor.databinding.AnswerResultItemBinding
import org.threehundredtutor.databinding.QuestionAnswerWithErrorsItemBinding
import org.threehundredtutor.databinding.QuestionDetailedAnswerItemBinding
import org.threehundredtutor.databinding.QuestionRightAnswerItemBinding
import org.threehundredtutor.databinding.SelectRightAnswerOrAnswersItemNewBinding
import org.threehundredtutor.databinding.SolutionAnswerTitleItemBinding
import org.threehundredtutor.databinding.SolutionCheckButtonItemBinding
import org.threehundredtutor.databinding.SolutionDividerItemBinding
import org.threehundredtutor.databinding.SolutionFooterItemBinding
import org.threehundredtutor.databinding.SolutionImageItemBinding
import org.threehundredtutor.databinding.SolutionSeparatorItemBinding
import org.threehundredtutor.databinding.SolutionSupSubItemBinding
import org.threehundredtutor.databinding.SolutionTextItemBinding
import org.threehundredtutor.databinding.SolutionTitleItemBinding
import org.threehundredtutor.databinding.SolutionYoutubeItemBinding
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.html_helper.DividerColor
import org.threehundredtutor.presentation.solution.html_helper.GravityAlign.Companion.getGravity
import org.threehundredtutor.presentation.solution.models.item.DividerSolutionItem
import org.threehundredtutor.presentation.solution.models.item.FooterSolutionItem
import org.threehundredtutor.presentation.solution.models.item.ImageSolutionItem
import org.threehundredtutor.presentation.solution.models.item.SeparatorSolutionItem
import org.threehundredtutor.presentation.solution.models.SolutionItem
import org.threehundredtutor.presentation.solution.models.item.SupSubSolutionItem
import org.threehundredtutor.presentation.solution.models.item.TextSolutionItem
import org.threehundredtutor.presentation.solution.models.answer.TitleAnswerUiItem
import org.threehundredtutor.presentation.solution.models.item.TitleSolutionItem
import org.threehundredtutor.presentation.solution.models.item.YoutubeUiItem
import org.threehundredtutor.presentation.solution.models.answer.AnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.models.answer.AnswerXUiModel
import org.threehundredtutor.presentation.solution.models.answer.DetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.models.answer.ResultAnswerUiModel
import org.threehundredtutor.presentation.solution.models.answer.RightAnswerUiModel
import org.threehundredtutor.presentation.solution.models.check.CheckButtonUiItem
import org.threehundredtutor.presentation.solution.models.check.ResultButtonUiItem


object SolutionDelegate {
    fun getTextHtmlItemDelegate() =
        adapterDelegateViewBinding<TextSolutionItem, SolutionItem, SolutionTextItemBinding>({ layoutInflater, root ->
            SolutionTextItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.textItem.text = item.text.fromHtml()
                binding.textItem.gravity = item.gravityAlign.getGravity()
            }
        }

    fun getSupSubHtmlItemDelegate() =
        adapterDelegateViewBinding<SupSubSolutionItem, SolutionItem, SolutionSupSubItemBinding>({ layoutInflater, root ->
            SolutionSupSubItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.textItem.text = item.text.fromHtml()
                binding.textItem.gravity = item.gravityAlign.getGravity()
            }
        }

    fun getImageHtmlItemDelegate(itemClickedListener: (String) -> Unit) =
        adapterDelegateViewBinding<ImageSolutionItem, SolutionItem, SolutionImageItemBinding>({ layoutInflater, root ->
            SolutionImageItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.solutionImage.loadServer(item.idImage)
                binding.solutionImage.setOnClickListener { itemClickedListener.invoke(item.idImage) }
            }
        }

    fun getTitleHtmlItemDelegate() =
        adapterDelegateViewBinding<TitleSolutionItem, SolutionItem, SolutionTitleItemBinding>({ layoutInflater, root ->
            SolutionTitleItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind { binding.solutionTitle.text = item.title }
        }

    fun getTitleAnswerItemDelegate() =
        adapterDelegateViewBinding<TitleAnswerUiItem, SolutionItem, SolutionAnswerTitleItemBinding>({ layoutInflater, root ->
            SolutionAnswerTitleItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind { binding.title.text = item.title }
        }

    fun getDividerHtmlItemDelegate(): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<DividerSolutionItem, SolutionItem, SolutionDividerItemBinding>({ layoutInflater, root ->
            SolutionDividerItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.viewDivider.setBackgroundResource(
                    when (item.dividerColor) {
                        DividerColor.BACKGROUND -> itemView.getColorAttr(R.attr.background)
                        DividerColor.CONTENT_BACKGROUND -> itemView.getColorAttr(R.attr.contentBackground)
                    }
                )
            }
        }

    fun getFooterHtmlItemDelegate(): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<FooterSolutionItem, SolutionItem, SolutionFooterItemBinding>({ layoutInflater, root ->
            SolutionFooterItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {}
        }

    fun getSeparatorHtmlItemDelegate(): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<SeparatorSolutionItem, SolutionItem, SolutionSeparatorItemBinding>(
            { layoutInflater, root ->
                SolutionSeparatorItemBinding.inflate(
                    layoutInflater, root, false
                )
            }) {
            bind {}
        }

    fun getYoutubeHtmlItemDelegate(itemClickedListener: (String) -> Unit): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<YoutubeUiItem, SolutionItem, SolutionYoutubeItemBinding>({ layoutInflater, root ->
            SolutionYoutubeItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            binding.openYoutube.setOnClickListener {
                itemClickedListener.invoke(item.link)
            }
            bind {}
        }

    fun getResultButtonUiItemDelegate(): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<ResultButtonUiItem, SolutionItem, AnswerButtonItemBinding>({ layoutInflater, root ->
            AnswerButtonItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.answerResultButton.bindResultType(item.answerValidationResultType)
            }
        }

    fun getResultAnswerDelegate(): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<ResultAnswerUiModel, SolutionItem, AnswerResultItemBinding>({ layoutInflater, root ->
            AnswerResultItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.correctAnswerValue.text = item.rightAnswer
                binding.yourAnswerValue.text = item.answer
                binding.answerResultButton.bindResultType(item.answerValidationResultType)
            }
        }

    fun getAnswerWithErrorsDelegate(itemClickedListener: (AnswerWithErrorsUiModel, String) -> Unit): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<AnswerWithErrorsUiModel, SolutionItem, QuestionAnswerWithErrorsItemBinding>(
            { layoutInflater, root ->
                QuestionAnswerWithErrorsItemBinding.inflate(
                    layoutInflater, root, false
                )
            }) {
            binding.checkButton.setOnClickListener { view ->
                val answer = binding.answerEditText.text.toString().trim()
                if (answer.isNotEmpty()) {
                    itemClickedListener.invoke(item, answer)
                }
                view.hideKeyboard()
            }
            bind {}
        }

    fun getRightAnswerDelegate(itemClickedListener: (RightAnswerUiModel, String) -> Unit): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<RightAnswerUiModel, SolutionItem, QuestionRightAnswerItemBinding>(
            { layoutInflater, root ->
                QuestionRightAnswerItemBinding.inflate(
                    layoutInflater, root, false
                )
            }) {

            binding.checkButton.setOnClickListener { view ->
                val answer = binding.answerEditText.text.toString().trim()
                if (answer.isNotEmpty()) {
                    itemClickedListener.invoke(item, answer)
                }
                view.hideKeyboard()
            }
            bind {
                if (item.caseInSensitive) {
                    binding.answerEditText.filters = arrayOf<InputFilter>(AllCaps())
                }
            }
        }

    fun getDetailedAnswerDelegate(itemClickedListener: (DetailedAnswerUiModel, String) -> Unit): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<DetailedAnswerUiModel, SolutionItem, QuestionDetailedAnswerItemBinding>(
            { layoutInflater, root ->
                QuestionDetailedAnswerItemBinding.inflate(
                    layoutInflater, root, false
                )
            }) {
            binding.checkButton.setOnClickListener {
                val answer = binding.answerEditText.text.toString().trim()
                if (answer.isNotEmpty()) {
                    itemClickedListener.invoke(item, answer)
                }
            }
            bind {}
        }

    fun getAnswerXDelegate(itemChecked: (String, String, Boolean) -> Unit): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<AnswerXUiModel, SolutionItem, SelectRightAnswerOrAnswersItemNewBinding>(
            { layoutInflater, root ->
                SelectRightAnswerOrAnswersItemNewBinding.inflate(
                    layoutInflater, root, false
                )
            }) {
            binding.checkbox.setOnCheckedChangeListener { view, cheked ->
                if (view.isPressed) {
                    itemChecked.invoke(item.questionId, item.answer, cheked)
                }
            }
            bind {
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
            }
        }


    fun getCheckButtonDelegate(itemChecked: (String) -> Unit): AdapterDelegate<List<SolutionItem>> =
        adapterDelegateViewBinding<CheckButtonUiItem, SolutionItem, SolutionCheckButtonItemBinding>(
            { layoutInflater, root ->
                SolutionCheckButtonItemBinding.inflate(layoutInflater, root, false)
            })
        {
            binding.checkButton.setOnClickListener {
                itemChecked.invoke(item.questionId)
            }
            bind {}
        }

    private fun MaterialButton.bindResultType(type: AnswerValidationResultType) {
        when (type) {
            AnswerValidationResultType.NOT_CORRECT_ANSWER -> {
                setBackgroundColor(getColorAttr(R.attr.warning, false))
                text = context.getString(R.string.incorrect_answer)
                setIconResource(R.drawable.ic_incorrect_answer)
            }

            AnswerValidationResultType.PARTIALLY_CORRECT_ANSWER -> {
                setBackgroundColor(getColorAttr(R.attr.primary, false))
                text = context.getString(R.string.allmost_the_right_answer)
                setIconResource(R.drawable.ic_allmost_answer)
            }

            AnswerValidationResultType.CORRECT_ANSWER -> {
                setBackgroundColor(getColorAttr(R.attr.defaultGreen, false))
                text = context.getString(R.string.correct_answer)
                setIconResource(R.drawable.ic_correct_answer)
            }

            else -> {}
        }
    }
}