package org.threehundredtutor.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.utils.PrefsSettingsDagger
import org.threehundredtutor.databinding.StartedFragmentBinding
import org.threehundredtutor.di.components.StartedComponent
import javax.inject.Inject

class StartedFragment : BaseFragment(R.layout.started_fragment) {

    private lateinit var binding: StartedFragmentBinding

    @Inject
    lateinit var prefsSettings: PrefsSettingsDagger

    override val viewModel: BaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = StartedFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInject() {
        StartedComponent.createStartedComponent().inject(this)
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
