package org.v_points.presentation.html_page.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import org.v_points.presentation.html_page.adapter.HtmlPageAdapters.getHtmlPageEmptyUiItem
import org.v_points.presentation.html_page.adapter.HtmlPageAdapters.getHtmlPageHeaderEmptyItemAdapter
import org.v_points.presentation.html_page.adapter.HtmlPageAdapters.getHtmlPageStartTestAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getDividerUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getFooterUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getImageUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSeparatorUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getSupSubUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getTextUiItemAdapter
import org.v_points.presentation.solution.adapter.SolutionAdapters.getYoutubeUiItemAdapter
import org.v_points.presentation.solution.ui_models.SolutionUiItem

class HtmlPageManager(
    imageClickListener: (String) -> Unit,
    youtubeClickListener: (String) -> Unit,
    firstStartTestClickListener: (Unit) -> Unit,
    secondStartTestClickListener: (Unit) -> Unit,
    fullStartTestClickListener: (Unit) -> Unit
) : AsyncListDifferDelegationAdapter<SolutionUiItem>(DIFF_CALLBACK) {

    init {
        delegatesManager
            .addDelegate(getFooterUiItemAdapter())
            .addDelegate(getImageUiItemAdapter(imageClickListener))
            .addDelegate(getTextUiItemAdapter())
            .addDelegate(getSupSubUiItemAdapter())
            .addDelegate(getDividerUiItemAdapter())
            .addDelegate(getYoutubeUiItemAdapter(youtubeClickListener))
            .addDelegate(getSeparatorUiItemAdapter())
            .addDelegate(getHtmlPageHeaderEmptyItemAdapter())
            .addDelegate(getHtmlPageEmptyUiItem())
            .addDelegate(
                getHtmlPageStartTestAdapter(
                    firstStartTestClickListener = firstStartTestClickListener,
                    secondStartTestClickListener = secondStartTestClickListener,
                    fullStartTestClickListener = fullStartTestClickListener
                )
            )
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SolutionUiItem>() {
            override fun areItemsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                return oldItem.javaClass == newItem.javaClass
            }

            override fun areContentsTheSame(
                oldItem: SolutionUiItem,
                newItem: SolutionUiItem
            ): Boolean {
                return oldItem.equals(newItem)
            }

            override fun getChangePayload(oldItem: SolutionUiItem, newItem: SolutionUiItem): Any? {
                return null
            }
        }
    }
}