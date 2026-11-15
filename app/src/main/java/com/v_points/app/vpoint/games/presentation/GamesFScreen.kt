package com.v_points.app.vpoint.games.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.AppLayout
import com.v_points.app.core.navigate
import com.v_points.app.presentation.subject_workspace.SubjectWorkspaceFragment.Companion.SUBJECT_WORKSPACE_WORKSPACE_ID_KEY
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.util_class.BundleString
import com.v_points.app.ui_core.databinding.MainFragmentBinding
import com.v_points.app.ui_core.setComposeContent
import com.v_points.app.vpoint.game_day.presentation.GameDayFragment.Companion.GAME_DAY_DATE
import com.v_points.app.vpoint.game_day.presentation.GameDayFragment.Companion.GAME_DAY_ID
import com.v_points.app.vpoint.game_day.presentation.MainScreenNavigateAction
import com.v_points.app.vpoint.games.di.GameComponentModel
import com.v_points.app.vpoint.games.di.GamesComponent

class GamesFScreen : BaseFragment(AppLayout.games_screen) {

    override val bottomMenuVisible: Boolean = false

    private val gameDayId by BundleString(GAME_DAY_ID, EMPTY_STRING)
    private val gameDayDate by BundleString(GAME_DAY_DATE, EMPTY_STRING)

    private val gamesComponent by lazy {
        GamesComponent.createGamesComponent(GameComponentModel(gameDayId, gameDayDate))
    }
    override val viewModel: GamesViewModel by viewModels {
        gamesComponent.viewModelMapFactory()
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
            GamesScreen(viewModel = viewModel)
        }
    }

    override fun onObserveData() {
        viewModel.getNavigateAction().observeFlow(this) { action ->
            when (action) {
                GameScreenNavigateAction.OnBackClick -> findNavController().popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
