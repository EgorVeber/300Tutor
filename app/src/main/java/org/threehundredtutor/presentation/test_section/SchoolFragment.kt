package org.threehundredtutor.presentation.test_section

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.test_section.SchoolComponent
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.LoadingDialog
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showSnack
import org.threehundredtutor.ui_core.SnackBarType
import org.threehundredtutor.ui_core.databinding.SchoolFragmentBinding

class SchoolFragment : BaseFragment(UiCoreLayout.school_fragment) {

    private lateinit var binding: SchoolFragmentBinding

    private val schoolComponent by lazy {
        SchoolComponent.createTestSectionComponent()
    }

    override val viewModel: SchoolViewModel by viewModels {
        schoolComponent.viewModelMapFactory()
    }

    private val delegateAdapter: SchoolManager = SchoolManager(
        schoolClickListener = { host, name ->
            viewModel.onSchoolClicked(host, name)
        },
    )

    override val bottomMenuVisible: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = SchoolFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.testSectionToolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.schoolRecycler.adapter = delegateAdapter
    }

    override fun onObserveData() {
        viewModel.getLoadingState().observeFlow(this) { loading ->
            if (loading) {
                LoadingDialog.show(requireActivity().supportFragmentManager)
            } else {
                LoadingDialog.close(requireActivity().supportFragmentManager)
            }
        }
        viewModel.getUiItems().observeFlow(this) { items ->
            delegateAdapter.items = items
        }
        viewModel.getMessagesStream().observeFlow(this) { message ->
            showSnack(title = message, backgroundColor = SnackBarType.SUCCESS.colorRes)
        }
        viewModel.getUiAction().observeFlow(this) { event ->
            when (event) {
                SchoolViewModel.NavigationEvent.AuthorizationScreen -> {
                    navigate(R.id.action_schoolFragment_to_authorizationFragment)
                }

                SchoolViewModel.NavigationEvent.HomeScreen -> {
                    navigate(R.id.action_schoolFragment_to_mainFragment)
                }

                SchoolViewModel.NavigationEvent.StartedScreen -> {
                    navigate(R.id.action_schoolFragment_to_startedFragment)
                }
            }
        }
    }
}