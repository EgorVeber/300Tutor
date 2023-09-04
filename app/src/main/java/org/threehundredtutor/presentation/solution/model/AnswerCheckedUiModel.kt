package org.threehundredtutor.presentation.solution.model

data class AnswerCheckedUiModel(
    val isRightAnswer: Boolean,
    val questionId: String,
    val text: String,
    var checked: Boolean,// TODO var переменная подумать над логикой адаптера.
    var enabled: Boolean,//
) : HtmlItem