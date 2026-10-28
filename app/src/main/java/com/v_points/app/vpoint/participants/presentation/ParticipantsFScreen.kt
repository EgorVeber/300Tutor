package com.v_points.app.vpoint.participants.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.v_points.app.R
import com.v_points.app.core.AppLayout
import com.v_points.app.core.navigate
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_core.databinding.MainFragmentBinding
import com.v_points.app.ui_core.setComposeContent
import com.v_points.app.vpoint.participants.di.ParticipantsComponent

class ParticipantsFScreen : BaseFragment(AppLayout.participants_screen) {

    override val bottomMenuVisible: Boolean = false
    private val participantsComponent by lazy {
        ParticipantsComponent.createParticipantsComponent()
    }
    override var customHandlerBackStack = true
    override val viewModel: ParticipantsViewModel by viewModels {
        participantsComponent.viewModelMapFactory()
    }

    override fun onBackPressed() {
        navigate(R.id.action_participantsFScreen_to_mainFragment)
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
            ParticipantsScreen(viewModel = viewModel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
