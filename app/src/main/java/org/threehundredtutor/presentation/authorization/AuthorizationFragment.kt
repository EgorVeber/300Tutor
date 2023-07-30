package org.threehundredtutor.presentation.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.databinding.AuthorizationFragmentLayoutBinding

class AuthorizationFragment : BaseFragment(R.layout.authorization_fragment_layout) {

    override val viewModel: AuthorizationViewModel by viewModels()

    lateinit var binding: AuthorizationFragmentLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AuthorizationFragmentLayoutBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView() {
        binding.passwordEditText.setText("VbAn@9873")
        binding.emailEditText.setText("newjamesohara@gmail.com")
        binding.signInButton.setOnClickListener {
            val password = binding.passwordEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            viewModel.onSignInButtonClicked(password, email)
        }
        binding.registrationButton.setOnClickListener {
            viewModel.onRegistrationButtonClicked()
        }
    }

    override fun onObserveData() {
        viewModel.getOpenScreenEventStateFlow().observeFlow(this) { screen ->
            when (screen) {
                AuthorizationViewModel.NavigateScreenState.NavigateHomeScreen ->
                    navigate(R.id.action_authorizationFragment_to_homeFragment)

                AuthorizationViewModel.NavigateScreenState.NavigateRegistrationScreen ->
                    navigate(R.id.action_authorizationFragment_to_registration)
            }
        }
        viewModel.getErrorEventStateFlow().observeFlow(this) { errorMessage ->
            showMessage(errorMessage)
        }
    }
}
