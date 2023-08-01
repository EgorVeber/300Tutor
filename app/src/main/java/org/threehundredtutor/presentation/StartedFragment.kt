package org.threehundredtutor.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.PrefsSettings
import org.threehundredtutor.databinding.StartedFragmentBinding

class StartedFragment : BaseFragment(R.layout.started_fragment) {

    private val prefsSettings by lazy { PrefsSettings }

    override val viewModel: BaseViewModel by viewModels()

    private lateinit var binding: StartedFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = StartedFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        super.onInitView()
        if (prefsSettings.getFirstStartApp()) {
            binding.domainSpinner.isVisible = true
            prefsSettings.setFirstStartApp()
        } else if (prefsSettings.getCurrentDomain().isNotEmpty()) {
            binding.titleStartedFragment.isVisible = false
            binding.domainSpinner.isVisible = false
            if (prefsSettings.getAccountLogin().isNotEmpty()) {
                findNavController().navigate(R.id.action_startedFragment_to_homeFragment)
            } else {
                //TODO логика перехода на авторизацию если уже зарегистрирован
                findNavController().navigate(R.id.action_startedFragment_to_registrationFragment)
            }
        } else {
            binding.domainSpinner.isVisible = true
            binding.titleStartedFragment.isVisible = true
            prefsSettings.setDomain("domain")// TODO потом удалить
        }
    }
}
