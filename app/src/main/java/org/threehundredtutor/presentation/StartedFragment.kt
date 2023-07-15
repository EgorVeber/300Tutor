package org.threehundredtutor.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.common.PrefsSettings
import org.threehundredtutor.databinding.StartedFragmentLayoutBinding

class StartedFragment : Fragment(R.layout.started_fragment_layout) {
    private val prefsSettings by lazy { PrefsSettings }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = StartedFragmentLayoutBinding.bind(view)
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
