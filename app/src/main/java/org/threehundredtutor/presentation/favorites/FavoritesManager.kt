package org.threehundredtutor.presentation.favorites

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.threehundredtutor.presentation.account.ItemTouchHelperAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getAnswerFavoritesSelectRightUiModelAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getDividerUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getEmptyUiItem
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getFavoritesAnswerWithErrorsResultUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getFooterUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getHeaderUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getImageUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSelectRightAnswerTitleUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSeparatorUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSuccessAnswerTitleUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getSupSubUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getTextUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getTouchUiItemAdapter
import org.threehundredtutor.presentation.solution.adapter.SolutionAdapters.getYoutubeUiItemAdapter
import org.threehundredtutor.presentation.solution.ui_models.SolutionUiItem
import org.threehundredtutor.presentation.solution.ui_models.item_common.HeaderUiItem
import org.threehundredtutor.presentation.solution.ui_models.select_right_answer.SelectRightAnswerUiModel


data class TouchUiModel(val title: String, val isQuestionLikedByStudent: Boolean)

class FavoritesManager(
    imageClickListener: (String) -> Unit,
    youtubeClickListener: (String) -> Unit,
    questionLikeClickListener: (HeaderUiItem) -> Unit
) : AsyncListDifferDelegationAdapter<SolutionUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getHeaderUiItemAdapter(questionLikeClickListener))
            .addDelegate(getFooterUiItemAdapter())
            .addDelegate(getTextUiItemAdapter())
            .addDelegate(getImageUiItemAdapter(imageClickListener))
            .addDelegate(getYoutubeUiItemAdapter(youtubeClickListener))
            .addDelegate(getSupSubUiItemAdapter())
            .addDelegate(getDividerUiItemAdapter())
            .addDelegate(getSeparatorUiItemAdapter())
            .addDelegate(getSelectRightAnswerTitleUiItemAdapter())
            .addDelegate(getAnswerFavoritesSelectRightUiModelAdapter())
            .addDelegate(getSuccessAnswerTitleUiItemAdapter())
            .addDelegate(getFavoritesAnswerWithErrorsResultUiItemAdapter())
            .addDelegate(getEmptyUiItem())
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionUiItem>() {
            override fun areItemsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                if (oldItem is SelectRightAnswerUiModel && newItem is SelectRightAnswerUiModel && oldItem.answer == newItem.answer) {
                    return true
                }
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }

            override fun getChangePayload(oldItem: SolutionUiItem, newItem: SolutionUiItem): Any? {
                return when {
                    oldItem is HeaderUiItem && newItem is HeaderUiItem -> {
                        return oldItem.isQuestionLikedByStudent != newItem.isQuestionLikedByStudent
                    }

                    else -> null
                }
            }
        }
    }
}

class TouchManager(
    private val onFinishedListener: () -> Unit,
    private val onItemMoveListener: (fromPosition: Int, toPosition: Int) -> Unit,
) : AsyncListDifferDelegationAdapter<TouchUiModel>(DIFF_CALLBACK), ItemTouchHelperAdapter {

    init {
        delegatesManager.addDelegate(getTouchUiItemAdapter())
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TouchUiModel>() {
            override fun areItemsTheSame(
                oldItem: TouchUiModel,
                newItem: TouchUiModel
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: TouchUiModel,
                newItem: TouchUiModel
            ): Boolean {
                return false
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Log.d(
            "TOPUCJHELPR",
            "onItemMove() called with: fromPosition = $fromPosition, toPosition = $toPosition"
        )
        notifyItemMoved(fromPosition, toPosition)
        onItemMoveListener(fromPosition, toPosition)
    }

    override fun onFinished() {
        Log.d("TOPUCJHELPR", "onFinished() called ")
        onFinishedListener()
    }
}

class SimpleItemTouchHelperCallback(private val mAdapter: ItemTouchHelperAdapter) :
    ItemTouchHelper.Callback() {

    private var startPosition = -1
    private var endPosition = -1
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun onSwiped(viewHolder: ViewHolder, direction: Int) {

    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: ViewHolder,
        target: ViewHolder
    ): Boolean {
        if (startPosition == -1) {
            startPosition = viewHolder.bindingAdapterPosition
        }
        endPosition = target.bindingAdapterPosition
        mAdapter.onItemMove(viewHolder.bindingAdapterPosition, target.bindingAdapterPosition)
        return true
    }

    override fun onSelectedChanged(viewHolder: ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (actionState == ItemTouchHelper.ACTION_STATE_IDLE) {
            Log.d(
                "onSelectedChanged",
                "onSelectedChanged() called with: startPosition = $startPosition, endPosition = $endPosition"
            )
            if (startPosition != endPosition && startPosition != -1) {
                mAdapter.onFinished()
                startPosition = -1
            } else {
                startPosition = -1
            }
        }
    }

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        return makeMovementFlags(dragFlags, -1)
    }
}