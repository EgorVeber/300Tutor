package com.v_points.app.vpoint.game_day_create.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.core.AppLayout
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.fragment.showSnack
import com.v_points.app.ui_core.setComposeContent
import com.v_points.app.vpoint.game_day_create.di.CreateGameDayComponent

class CreateGameDayFragment : BaseFragment(AppLayout.create_game_day_fragment) {

    override val bottomMenuVisible: Boolean = false

    private val component by lazy {
        CreateGameDayComponent.createCreateGameDayComponent()
    }

    override val viewModel: CreateGameDayViewModel by viewModels {
        component.viewModelMapFactory()
    }

    override fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as androidx.compose.ui.platform.ComposeView).setComposeContent(insets = null) {
            CreateGameDayScreen(
                viewModel = viewModel,
                onBackClick = { findNavController().popBackStack() }
            )
        }
    }

    override fun onObserveData() {
        viewModel.getNavigateAction().observeFlow(this) { action ->
            when (action) {
                is CreateGameDayNavigateAction.ShowSnack -> {
                    showSnack(
                        title = action.message,
                        backgroundColor = action.snackBarType.colorRes
                    )
                    findNavController().popBackStack()
                }

                CreateGameDayNavigateAction.SuccessCreated -> {
                    findNavController().popBackStack()
                }
            }
        }
    }
}
