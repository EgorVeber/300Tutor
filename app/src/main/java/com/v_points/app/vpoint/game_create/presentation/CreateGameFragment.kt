package com.v_points.app.vpoint.game_create.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.core.AppLayout
import com.v_points.app.core.UiCoreAttr
import com.v_points.app.ui_common.EMPTY_STRING
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.applyWindowColor
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.fragment.showSnack
import com.v_points.app.ui_common.util_class.BundleString
import com.v_points.app.ui_core.setComposeContent
import com.v_points.app.vpoint.game_day.presentation.GameDayFragment.Companion.GAME_DAY_ID
import com.v_points.app.vpoint.game_create.di.CreateGameComponent
import com.v_points.app.vpoint.game_create.di.CreateGameComponentModel

class CreateGameFragment : BaseFragment(AppLayout.create_game_fragment) {

    override val bottomMenuVisible: Boolean = false

    private val gameDayId by BundleString(GAME_DAY_ID, EMPTY_STRING)

    private val component by lazy {
        CreateGameComponent.createCreateGameComponent(CreateGameComponentModel(gameDayId))
    }

    override val viewModel: CreateGameViewModel by viewModels {
        component.viewModelMapFactory()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as androidx.compose.ui.platform.ComposeView).setComposeContent(insets = null) {
            CreateGameScreen(
                viewModel = viewModel,
                onBackClick = { findNavController().popBackStack() }
            )
        }
    }

    override fun onObserveData() {
        viewModel.getNavigateAction().observeFlow(this) { action ->
            when (action) {
                is CreateGameNavigateAction.ShowSnack -> {
                    showSnack(
                        title = action.message,
                        backgroundColor = action.snackBarType.colorRes
                    )
                }
                CreateGameNavigateAction.SuccessCreated -> {
                    findNavController().popBackStack()
                }
            }
        }
    }
}
