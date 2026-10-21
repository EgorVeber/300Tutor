package com.v_points.app.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.FirebaseFirestore
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.di.main.MainComponent
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.theme.TutorTheme

class MainFragment : BaseFragment(UiCoreLayout.main_fragment) {

    private val db by lazy { FirebaseFirestore.getInstance() }

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
