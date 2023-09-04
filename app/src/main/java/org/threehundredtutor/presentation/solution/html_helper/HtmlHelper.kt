package org.threehundredtutor.presentation.solution.html_helper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.threehundredtutor.domain.solution.models.test_model.QuestionModel
import org.threehundredtutor.domain.solution.models.test_model.TestQuestionType
import org.threehundredtutor.presentation.solution.mapper.toQuestionAnswerWithErrorsUiModel
import org.threehundredtutor.presentation.solution.mapper.toQuestionDetailedAnswerUiModel
import org.threehundredtutor.presentation.solution.mapper.toSelectRightAnswerOrAnswersUiModel
import org.threehundredtutor.presentation.solution.model.DividerHtmlItem
import org.threehundredtutor.presentation.solution.model.FooterHtmlItem
import org.threehundredtutor.presentation.solution.model.HtmlItem
import org.threehundredtutor.presentation.solution.model.ImageHtmlItem
import org.threehundredtutor.presentation.solution.model.SupSubHtmlItem
import org.threehundredtutor.presentation.solution.model.TextHtmlItem
import org.threehundredtutor.presentation.solution.model.TitleHtmlItem


// TODO Сделать через даггер
object HtmlHelper {

    private var bodyChildList: MutableList<HtmlItem> = mutableListOf()
    private const val COUNT_DIVIDER_BOTTOM_QUESTION = 4

    private const val FILE_IMAGE_TAG = "file-image"
    private const val SUP_TEXT_TAG = "sup"
    private const val SUB_TEXT_TAG = "sub"
    private const val H_ALIGN_ATTR = "h-align"
    private const val FILE_ID_ATTR = "file-id"

    fun getHtmlItemList(
        questionModel: QuestionModel,
        solutionId: String,
        countDividerBottomQuestion: Int = COUNT_DIVIDER_BOTTOM_QUESTION,
    ): List<HtmlItem> {

        val titleBodyMarkUp = questionModel.titleBodyMarkUp
        val title = questionModel.title
        val testQuestionType = questionModel.testQuestionType
        val doc: Document = Jsoup.parse(titleBodyMarkUp)
        val elements: Elements = doc.body().children()

        bodyChildList = mutableListOf()
        bodyChildList.add(TitleHtmlItem(title))

        elements.forEach { element ->
            val itemImage: Elements = element.getElementsByTag(FILE_IMAGE_TAG)
            val itemSub: Elements = element.getElementsByTag(SUP_TEXT_TAG)
            val itemSup: Elements = element.getElementsByTag(SUB_TEXT_TAG)

            if (itemImage.size == 0) {
                val text: String = element.text()
                if (text.isNotEmpty()) {
                    val attrAlign = element.attr(H_ALIGN_ATTR)
                    val gravityAlign: GravityAlign = GravityAlign.getGravity(attrAlign)
                    val isTextItem = itemSub.size == 0 && itemSup.size == 0
                    if (isTextItem) {
                        bodyChildList.add(TextHtmlItem(element.toString(), gravityAlign))
                    } else {
                        bodyChildList.add(SupSubHtmlItem(element.toString(), gravityAlign))
                    }
                } else {
                    bodyChildList.add(DividerHtmlItem(DividerColor.CONTENT_BACKGROUND))
                }
            } else {
                bodyChildList.add(ImageHtmlItem(element.attr(FILE_ID_ATTR)))
            }
        }

        // bodyChildList.add(SeparatorHtmlItem())
        //  bodyChildList.add(DividerHtmlItem(DividerColor.CONTENT_BACKGROUND))
        //  bodyChildList.add(DividerHtmlItem(DividerColor.CONTENT_BACKGROUND))

        when (testQuestionType) {
            TestQuestionType.SELECT_RIGHT_ANSWER_OR_ANSWERS -> {
                bodyChildList.add(
                    questionModel.toSelectRightAnswerOrAnswersUiModel(
                        solutionId = solutionId,
                        id = questionModel.id
                    )
                )
            }

            TestQuestionType.TYPE_RIGHT_ANSWER -> {

            }

            TestQuestionType.DETAILED_ANSWER -> {
                bodyChildList.add(questionModel.toQuestionDetailedAnswerUiModel(solutionId = solutionId))
            }

            TestQuestionType.TYPE_ANSWER_WITH_ERRORS -> {
                bodyChildList.add(
                    questionModel.toQuestionAnswerWithErrorsUiModel(
                        solutionId = solutionId,
                        rightAnswer = questionModel.typeAnswerWithErrorsDataModel.rightAnswer
                    )
                )
            }

            TestQuestionType.UNKNOWN -> {

            }
        }

        bodyChildList.add(FooterHtmlItem())

        return finishExtract(countDividerBottomQuestion)
    }

    private fun finishExtract(countDividerBottomQuestion: Int) = bodyChildList.apply {
        repeat(countDividerBottomQuestion) { add(DividerHtmlItem(DividerColor.BACKGROUND)) }
    }.toList()
}






