package org.threehundredtutor.presentation.authorization

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.databinding.AuthorizationFragmentBinding
import org.threehundredtutor.di.components.AuthorizationComponent
import org.threehundredtutor.presentation.common.LoadingDialog

class AuthorizationFragment : BaseFragment(R.layout.authorization_fragment) {

    private val authorizationComponent by lazy {
        AuthorizationComponent.createAuthorizationComponent()
    }

    override val bottomMenuVisible: Boolean = false
    override var customHandlerBackStackWithDelay: Boolean = true

    override val viewModel by viewModels<AuthorizationViewModel> {
        authorizationComponent.viewModelMapFactory()
    }

    private lateinit var binding: AuthorizationFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AuthorizationFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) = with(binding) {
        emailTextInput.requestFocus()

        signInButton.setOnClickListener {
            val password = passwordEditText.text.toString()
            val phone = phoneInputEt.text.toString()
            val email = emailEditText.text.toString()
            viewModel.onSignInButtonClicked(password, email, phone)
        }

        registrationButton.setOnClickListener {
            viewModel.onRegistrationButtonClicked()
        }

        imageContainer.setOnClickListener {
            passwordEditText.setText("1234@Abc")
            viewModel.onImageClicked(emailTextInput.isVisible)
        }

        forgotTextView.setOnClickListener {
            navigate(R.id.action_authorizationFragment_to_restorPasswordFragment)
        }

        //TODO Test--Удалить
        tutorImage.setOnClickListener {
            passwordEditText.setText("Qwe123qwe")
            emailEditText.setText("James@e-mail.ru")
            phoneInputEt.setText("9208309193")
        }
        tutorImage.setOnLongClickListener {
            passwordEditText.setText("")
            emailEditText.setText("")
            phoneInputEt.setText("")
            true
        }
        //TODO Test--Удалить
    }

    override fun onObserveData() {
        viewModel.getOpenScreenEventStateFlow().observeFlow(this) { screen ->
            when (screen) {
                is AuthorizationViewModel.NavigateScreenState.NavigateHomeScreen -> {
                    navigate(R.id.action_authorizationFragment_to_homeFragment)
                }

                AuthorizationViewModel.NavigateScreenState.NavigateRegistrationScreen ->
                    navigate(R.id.action_authorizationFragment_to_registration)
            }
        }
        viewModel.getErrorEventStateFlow().observeFlow(this) { errorMessage ->
            showMessage(errorMessage)
        }
        viewModel.getInputTypeStateFlow().observeFlow(this) { inputTypeState ->
            when (inputTypeState) {
                AuthorizationViewModel.InputTypeState.Email -> showInputType(true)
                AuthorizationViewModel.InputTypeState.Phone -> showInputType(false)
            }
        }

        viewModel.getLoadingStateFlow().observeFlow(this) { loading ->
            if (loading) {
                LoadingDialog.show(requireActivity().supportFragmentManager)
            } else {
                LoadingDialog.close(requireActivity().supportFragmentManager)
            }
        }
    }

    private fun showInputType(visible: Boolean) {
        TransitionSet().apply {
            addTransition(Fade())
            TransitionManager.beginDelayedTransition(binding.root, this)
        }
        binding.emailTextInput.isVisible = visible
        binding.emailEditText.isVisible = visible
        binding.phoneOne.isVisible = !visible
        binding.phoneInput.isVisible = !visible
        binding.phoneInputEt.isVisible = !visible
        binding.phoneOneEt.isVisible = !visible
        binding.imagePhone.isVisible = !visible

        if (visible) {
            binding.imagePhoneOrEmail.setImageResource(R.drawable.ic_phone)
            binding.emailEditText.requestFocus()
        } else {
            binding.phoneInputEt.requestFocus()
            binding.imagePhoneOrEmail.setImageResource(R.drawable.ic_mail)
        }
    }
}
