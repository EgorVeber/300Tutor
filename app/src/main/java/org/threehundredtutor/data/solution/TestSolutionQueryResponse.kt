package org.threehundredtutor.data.solution

data class TestSolutionQueryResponse(
    var solutionId: String,
    var hasCuratorValidation: Boolean,
    var canCheckSingleQuestion: Boolean,
    var studentGroupId: String,
    var studentId: String,
    var startedOnUtc: String,
    var finishedOnUtc: String,
    var isFinished: Boolean,

    var solution: Solution,

    var test: Test
)

data class Solution(
    var answers: List<Answer>
)
data class Answer(
    var answerOrAnswers: String,
    var isChecked: Boolean,
    var pointsValidation: PointsValidation,
    var questionId: String,
    var questionVersionId: String,
    var resultType: String
)
data class PointsValidation(
    var answerPoints: Int,
    var description: String,
    var isValidated: Boolean,
    var questionTotalPoints: Int
)




data class Test(
    var description: String,
    var name: String,
    var questions: List<Question>
)
data class Question(
    var answerExplanationMarkUp: String,
    var answerExplanationMarkUpMobile: String,
    var helpBodyMarkUp: String,
    var helpBodyMarkUpMobile: String,
    var id: String,
    var selectRightAnswerOrAnswersData: SelectRightAnswerOrAnswersData,
    var title: String,
    var titleBodyMarkUp: String,
    var titleBodyMarkUpMobile: String,
    var type: String,
    var typeAnswerWithErrorsData: TypeAnswerWithErrorsData,
    var typeRightAnswerQuestionData: TypeRightAnswerQuestionData,
    var versionId: String
)
data class TypeRightAnswerQuestionData(
    var caseInSensitive: Boolean,
    var rightAnswers: List<String>
)
data class TypeAnswerWithErrorsData(
    var rightAnswer: String
)
data class SelectRightAnswerOrAnswersData(
    var answers: List<AnswerX>,
    var rightAnswersCount: Int,
    var selectRightAnswerTitle: String
)
data class AnswerX(
    var isRightAnswer: Boolean,
    var text: String
)