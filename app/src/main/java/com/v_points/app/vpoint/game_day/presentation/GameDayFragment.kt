package com.v_points.app.vpoint.game_day.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.navigate
import com.v_points.app.di.main.GameDayComponent
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_SUB_TITLE_WORKSPACE_OR_DIRECTORY_NAME
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_TITLE_SUBJECT_OR_DIRECTORY_KEY
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_WORKSPACE_ID_KEY
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.MainFragmentBinding
import com.v_points.app.ui_core.setComposeContent
import com.v_points.app.vpoint.game_day.MainScreenRoute

class GameDayFragment : BaseFragment(UiCoreLayout.main_fragment) {

    private val gameDayComponent by lazy {
        GameDayComponent.createHomeComponent()
    }

    override var customHandlerBackStackWithDelay = true

    override val viewModel: GameDaysViewModel by viewModels {
        gameDayComponent.viewModelMapFactory()
    }

    private var _binding: MainFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setComposeContent {
            MainScreenRoute(viewModel = viewModel)
        }
    }

    override fun onObserveData() {
        viewModel.getNavigateAction().observeFlow(this) { action ->
            when (action) {
                is MainScreenNavigateAction.GamesScreen -> {
                    navigate(
                        resId = R.id.action_mainFragment_to_gamesFScreen,
                        bundle = bundleOf(GAME_DAY_ID to action.gameDayId)
                    )
                }

                MainScreenNavigateAction.Profile -> {}

                MainScreenNavigateAction.Users -> {
                    navigate(R.id.action_mainFragment_to_participantsFScreen)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val GAME_DAY_ID = "GAME_DAY_ID"
    }
}
