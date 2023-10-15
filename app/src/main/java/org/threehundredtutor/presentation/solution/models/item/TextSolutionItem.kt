package org.threehundredtutor.presentation.solution.models.item

import org.threehundredtutor.presentation.solution.html_helper.GravityAlign
import org.threehundredtutor.presentation.solution.models.SolutionItem

data class TextSolutionItem(val text: String, val gravityAlign: GravityAlign) : SolutionItem