package org.threehundredtutor.presentation.solution

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.mahozad.android.PieChart.Slice
import org.threehundredtutor.R
import org.threehundredtutor.common.getColorAttr
import org.threehundredtutor.common.utils.BundleParcelable
import org.threehundredtutor.databinding.SolutionResultDialogBinding
import org.threehundredtutor.presentation.solution.ui_models.ResultTestUiModel
import kotlin.math.abs

class SolutionResultDialogFragment : BottomSheetDialogFragment() {
    lateinit var binding: SolutionResultDialogBinding
    private var resultTest by BundleParcelable<ResultTestUiModel>(BUNDLE_ARGUMENT_KEY)
    override fun getTheme() = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SolutionResultDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView()
    }


    private fun onInitView() {
        with(binding) {
            pieChart.slices = getSlices()
            percentResultInfo.text = resultTest.resultPercent
            titlePoints.text = resultTest.titlePoint
            resultTestRightQuestion.text = resultTest.resultTestRightQuestion
            resultTestNeedToCheck.text = resultTest.questionCountNeedCheckText
            resultTestNeedToCheck.isVisible = resultTest.questionCountNeedCheckText.isNotEmpty()
        }
    }

    private fun getSlices() = listOf(
        Slice(
            fraction = resultTest.fractionAnswer,
            color = getColorAttr(R.attr.primary, needResId = false)
        ),
        Slice(
            fraction = abs(1 - resultTest.fractionAnswer),
            color = getColorAttr(R.attr.primary40, needResId = false)
        )
    )

    companion object {
        private const val BUNDLE_ARGUMENT_KEY = "BUNDLE_ARGUMENT_KEY"
        private val SolutionResultDialogFragmentTag =
            SolutionResultDialogFragment::class.java.simpleName

        fun showDialog(
            resultTestUiModel: ResultTestUiModel,
            childFragmentManager: FragmentManager
        ) {
            if (childFragmentManager.findFragmentByTag(SolutionResultDialogFragmentTag) == null) {
                SolutionResultDialogFragment().apply {
                    this.resultTest = resultTestUiModel
                    show(childFragmentManager, SolutionResultDialogFragmentTag)
                }
            }
        }
    }
}