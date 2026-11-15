package com.v_points.app.vpoint.game_day_create.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.core.AppLayout
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.MainFragmentBinding
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
            CreateGameDayScreen(
                viewModel = viewModel,
                onBackClick = { findNavController().popBackStack() }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
