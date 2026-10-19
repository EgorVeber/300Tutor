package org.v_points.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import org.v_points.core.UiCoreLayout
import org.v_points.di.main.MainComponent
import org.v_points.ui_common.fragment.base.BaseFragment
import org.v_points.ui_core.theme.TutorTheme

class MainFragment : BaseFragment(UiCoreLayout.main_fragment) {

    private val mainComponent by lazy {
        MainComponent.createHomeComponent()
    }

    override var customHandlerBackStackWithDelay = true

    override val viewModel: MainViewModel by viewModels {
        mainComponent.viewModelMapFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        ComposeView(requireContext()).apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                TutorTheme {
                    MyFeatureScreen(viewModel = viewModel)
                }
            }
        }

    companion object {
        const val ACTIVATE_KEY_KEY = "ACTIVATE_KEY_KEY"
        const val ACTIVATE_KEY_DATA = "ACTIVATE_KEY_DATA"

        const val SUBJECT_DETAILED_KEY_ID = "SUBJECT_DETAILED_KEY_ID"
        const val SUBJECT_DETAILED_KEY_NAME = "SUBJECT_DETAILED_KEY_NAME"
    }
}
