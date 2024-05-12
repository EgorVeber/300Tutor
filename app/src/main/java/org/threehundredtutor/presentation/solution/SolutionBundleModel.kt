package org.threehundredtutor.presentation.solution

import org.threehundredtutor.domain.solution.models.test_model.HtmlPageTestType

class SolutionBundleModel(
    val testId: String, // Начать тест
    val solutionId: String, // Получить решение теста
    val directoryTestId: String, // Генерация теста по директории
    val workSpaceId: String,// Генерация теста по директории
    val htmlPageTestType: HtmlPageTestType,// Тип для генирации теста по директории.
)