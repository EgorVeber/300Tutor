package org.threehundredtutor.presentation.authorization

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreDrawable
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.authorization.AuthorizationComponent
import org.threehundredtutor.presentation.restore.RestorePasswordDialogFragment
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.LoadingDialog
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showMessage
import org.threehundredtutor.ui_core.databinding.AuthorizationFragmentBinding

class AuthorizationFragment : BaseFragment(UiCoreLayout.authorization_fragment) {

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
            viewModel.onImageClicked(emailTextInput.isVisible)
        }

        forgotTextView.setOnClickListener {
            RestorePasswordDialogFragment.showDialog(childFragmentManager)
        }

        //TODO Test--Удалить
        binding.changeSchoolButton.setOnClickListener {
            navigate(R.id.action_authorizationFragment_to_testSectionFragment)
        }
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
        viewModel.getChangeDomainState().observeFlow(this) { visible ->
            binding.changeSchoolButton.isVisible = visible
        }
        viewModel.getSchoolNameState().observeFlow(this) { title ->
            binding.authorizationToolbar.setSubtitle(title)
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
            binding.imagePhoneOrEmail.setImageResource(UiCoreDrawable.ic_phone)
            binding.emailEditText.requestFocus()
        } else {
            binding.phoneInputEt.requestFocus()
            binding.imagePhoneOrEmail.setImageResource(UiCoreDrawable.ic_mail)
        }
    }
}
