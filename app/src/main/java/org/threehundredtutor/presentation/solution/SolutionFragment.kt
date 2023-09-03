package org.threehundredtutor.presentation.solution

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.databinding.SolutionFragmentBinding
import org.threehundredtutor.di.account.AccountComponent
import org.threehundredtutor.di.solution.SolutionComponent

class SolutionFragment : BaseFragment(R.layout.solution_fragment) {

    private val solutionComponent by lazy {
        SolutionComponent.createSolutionComponent()
    }

    override val viewModel by viewModels<SolutionViewModel> {
        solutionComponent.viewModelMapFactory()
    }

    private lateinit var binding: SolutionFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SolutionFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        super.onInitView()
    }

    override fun onObserveData() {
    }
}

