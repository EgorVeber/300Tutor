package org.threehundredtutor.presentation.solution.adapter

import android.graphics.Rect
import android.view.View
import androidx.annotation.Dimension
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.RecyclerView

class SolutionItemDecoration(
    @Dimension private val paddingItems: Float
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        rect: Rect,
        view: View,
        parent: RecyclerView,
        s: RecyclerView.State
    ) {
        view.updatePadding(left = paddingItems.toInt(), right = paddingItems.toInt())
    }
}