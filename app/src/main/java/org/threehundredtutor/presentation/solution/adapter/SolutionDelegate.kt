package org.threehundredtutor.presentation.solution.adapter

import androidx.core.view.isVisible
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import org.threehundredtutor.R
import org.threehundredtutor.common.fromHtml
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.common.loadServer
import org.threehundredtutor.databinding.AnswerWithErrorsItemBinding
import org.threehundredtutor.databinding.QuestionAnswerWithErrorsItemBinding
import org.threehundredtutor.databinding.QuestionDetailedAnswerItemBinding
import org.threehundredtutor.databinding.SelectRightAnswerOrAnswersItemBinding
import org.threehundredtutor.databinding.SelectRightAnswerOrAnswersItemItemBinding
import org.threehundredtutor.databinding.SolutionDividerItemBinding
import org.threehundredtutor.databinding.SolutionFooterItemBinding
import org.threehundredtutor.databinding.SolutionImageItemBinding
import org.threehundredtutor.databinding.SolutionSeparatorItemBinding
import org.threehundredtutor.databinding.SolutionSupSubItemBinding
import org.threehundredtutor.databinding.SolutionTextItemBinding
import org.threehundredtutor.databinding.SolutionTitleItemBinding
import org.threehundredtutor.domain.solution.models.solution_models.AnswerValidationResultType
import org.threehundredtutor.presentation.solution.html_helper.DividerColor
import org.threehundredtutor.presentation.solution.html_helper.GravityAlign.Companion.getGravity
import org.threehundredtutor.presentation.solution.model.AnswerCheckedUiModel
import org.threehundredtutor.presentation.solution.model.AnswerUiModel
import org.threehundredtutor.presentation.solution.model.DividerHtmlItem
import org.threehundredtutor.presentation.solution.model.FooterHtmlItem
import org.threehundredtutor.presentation.solution.model.HtmlItem
import org.threehundredtutor.presentation.solution.model.ImageHtmlItem
import org.threehundredtutor.presentation.solution.model.QuestionAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.model.QuestionDetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.model.SelectRightAnswerOrAnswersUiModel
import org.threehundredtutor.presentation.solution.model.SeparatorHtmlItem
import org.threehundredtutor.presentation.solution.model.SupSubHtmlItem
import org.threehundredtutor.presentation.solution.model.TextHtmlItem
import org.threehundredtutor.presentation.solution.model.TitleHtmlItem

object SolutionDelegate {
    fun getTextHtmlItemDelegate() =
        adapterDelegateViewBinding<TextHtmlItem, HtmlItem, SolutionTextItemBinding>({ layoutInflater, root ->
            SolutionTextItemBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }) {
            bind {
                binding.textItem.text = item.text.fromHtml()
                binding.textItem.gravity = item.gravityAlign.getGravity()
            }
        }

    fun getSupSubHtmlItemDelegate() =
        adapterDelegateViewBinding<SupSubHtmlItem, HtmlItem, SolutionSupSubItemBinding>({ layoutInflater, root ->
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
        adapterDelegateViewBinding<ImageHtmlItem, HtmlItem, SolutionImageItemBinding>({ layoutInflater, root ->
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
        adapterDelegateViewBinding<TitleHtmlItem, HtmlItem, SolutionTitleItemBinding>({ layoutInflater, root ->
            SolutionTitleItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind { binding.solutionTitle.text = item.title }
        }

    fun getDividerHtmlItemDelegate(): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<DividerHtmlItem, HtmlItem, SolutionDividerItemBinding>({ layoutInflater, root ->
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

    fun getFooterHtmlItemDelegate(): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<FooterHtmlItem, HtmlItem, SolutionFooterItemBinding>({ layoutInflater, root ->
            SolutionFooterItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {}
        }

    fun getSeparatorHtmlItemDelegate(): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<SeparatorHtmlItem, HtmlItem, SolutionSeparatorItemBinding>({ layoutInflater, root ->
            SolutionSeparatorItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {}
        }

    fun getQuestionAnswerWithErrorsHtmlItemDelegate(itemClickedListener: (QuestionAnswerWithErrorsUiModel, String) -> Unit): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<QuestionAnswerWithErrorsUiModel, HtmlItem, QuestionAnswerWithErrorsItemBinding>(
            { layoutInflater, root ->
                QuestionAnswerWithErrorsItemBinding.inflate(
                    layoutInflater, root, false
                )
            }) {
            bind {
                // TODO обработка ввод текста
                binding.checkButton.setOnClickListener {
                    val answer = binding.answerEditText.text.toString().trim()
                    if (answer.isNotEmpty()) {
                        itemClickedListener.invoke(item, answer)
                    }
                }
            }
        }

    fun getQuestionDetailedAnswerHtmlItemDelegate(itemClickedListener: (QuestionDetailedAnswerUiModel, String) -> Unit): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<QuestionDetailedAnswerUiModel, HtmlItem, QuestionDetailedAnswerItemBinding>(
            { layoutInflater, root ->
                QuestionDetailedAnswerItemBinding.inflate(
                    layoutInflater, root, false
                )
            }) {
            bind {
                // TODO обработка ввод текста
                binding.checkButton.setOnClickListener {
                    val answer = binding.answerEditText.text.toString().trim()
                    if (answer.isNotEmpty()) {
                        itemClickedListener.invoke(item, answer)
                    }
                }
            }
        }


    fun getAnswerHtmlItemDelegate(): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<AnswerUiModel, HtmlItem, AnswerWithErrorsItemBinding>({ layoutInflater, root ->
            AnswerWithErrorsItemBinding.inflate(
                layoutInflater, root, false
            )
        }) {
            bind {
                binding.correctAnswerValue.text = item.rightAnswer
                binding.yourAnswerValue.text = item.answer
                when (item.answerValidationResultType) {
                    AnswerValidationResultType.NEED_TO_CHECK_BY_YOUR_SELF -> {
                        // Нужно проверить саостоятельно дургой item возможно
                    }

                    AnswerValidationResultType.NOT_CORRECT_ANSWER -> {
                        binding.answerResultButton.setBackgroundColor(
                            itemView.getColorAttr(R.attr.warning, false)
                        )
                        binding.answerResultButton.text =
                            itemView.context.getString(R.string.incorrect_answer)
                        binding.answerResultButton.setIconResource(R.drawable.ic_incorrect_answer)
                    }

                    AnswerValidationResultType.PARTIALLY_CORRECT_ANSWER -> {
                        binding.answerResultButton.setBackgroundColor(
                            itemView.getColorAttr(R.attr.primary, false)
                        )
                        binding.answerResultButton.text =
                            itemView.context.getString(R.string.allmost_the_right_answer)
                        binding.answerResultButton.setIconResource(R.drawable.ic_allmost_answer)
                    }

                    AnswerValidationResultType.CORRECT_ANSWER -> {
                        binding.answerResultButton.setBackgroundColor(
                            itemView.getColorAttr(R.attr.defaultGreen, false)
                        )
                        binding.answerResultButton.text =
                            itemView.context.getString(R.string.correct_answer)
                        binding.answerResultButton.setIconResource(R.drawable.ic_correct_answer)
                    }

                    AnswerValidationResultType.UNKNOWN -> {
                        throw IllegalArgumentException(" Answer result UNKNOWN type")
                    }
                }
            }
        }

    fun getSelectRightAnswerOrAnswersUiModelHtmlItemDelegate(
        itemClickedListener: (SelectRightAnswerOrAnswersUiModel) -> Unit,
        itemChecked: (String, Boolean, String) -> Unit
    ): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<SelectRightAnswerOrAnswersUiModel, HtmlItem, SelectRightAnswerOrAnswersItemBinding>(
            { layoutInflater, root ->
                SelectRightAnswerOrAnswersItemBinding.inflate(layoutInflater, root, false)
            }) {
            bind {
                val adapter = AsyncListDifferDelegationAdapter(
                    SolutionManager.DIFF_CALLBACK,
                    getSelectRightAnswerOrAnswersItemHtmlItemDelegate(itemChecked)
                )
                binding.recyclerSelectAnswers.adapter = adapter
                adapter.items = item.answers
                binding.title.text = item.selectRightAnswerTitle
                binding.checkButton.setOnClickListener {
                    if (item.answers.all { !it.checked }) return@setOnClickListener
                    itemClickedListener.invoke(item)
                    adapter.items = item.answers.map { it.copy(enabled = false) }
                    adapter.notifyDataSetChanged()// TODO оптимизировать и переделать все.
                }
            }
        }

    private fun getSelectRightAnswerOrAnswersItemHtmlItemDelegate(itemChecked: (String, Boolean, String) -> Unit): AdapterDelegate<List<HtmlItem>> =
        adapterDelegateViewBinding<AnswerCheckedUiModel, HtmlItem, SelectRightAnswerOrAnswersItemItemBinding>(
            { layoutInflater, root ->
                SelectRightAnswerOrAnswersItemItemBinding.inflate(
                    layoutInflater,
                    root,
                    false
                )
            }) {
            bind {
                if (item.isRightAnswer) {
                    binding.iconCorrect.isVisible = !item.enabled
                } else {
                    binding.iconIncorrect.isVisible = !item.enabled
                }

                binding.checkbox.text = item.text
                binding.checkbox.isChecked = item.checked
                binding.checkbox.isEnabled = item.enabled
                binding.checkbox.setOnCheckedChangeListener { compoundButton, cheked ->
                    item.checked = cheked
                    itemChecked.invoke(item.text, cheked, item.questionId)
                }
            }
        }
}