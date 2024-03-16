package org.threehundredtutor.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.UiCoreStrings
import org.threehundredtutor.di.registration.RegistrationComponent
import org.threehundredtutor.presentation.registration.viewmodel.RegistrationViewModel
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.base.BaseFragment
import org.threehundredtutor.ui_common.fragment.showMessage
import org.threehundredtutor.ui_core.databinding.RegistrationFragmentBinding

class RegistrationFragment : BaseFragment(UiCoreLayout.registration_fragment) {

    lateinit var binding: RegistrationFragmentBinding

    override val bottomMenuVisible: Boolean = false
    override var customHandlerBackStackWithDelay = true

    override val viewModel by viewModels<RegistrationViewModel> {
        registrationComponent.viewModelMapFactory()
    }

    private val registrationComponent by lazy {
        RegistrationComponent.createRegistrationComponent()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = RegistrationFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) {
        binding.buttonRegister.setOnClickListener {
            val passwordText = binding.etPassword.text.toString()
            if (passwordText.length < 6 || passwordText.contains("[0-9]".toRegex()).not()) {
                showMessage(getString(UiCoreStrings.password_wrong))
                return@setOnClickListener
            }
            if (binding.etEmail.text.toString().contains("@").not()) {
                showMessage(getString(UiCoreStrings.email_wrong))
                return@setOnClickListener
            }
            if (binding.etPhohe.text.toString().isEmpty()) {
                showMessage(getString(UiCoreStrings.phone_wrong))
                return@setOnClickListener
            }
            if (binding.checkbox.isEnabled) {
                viewModel.registerStudent(
                    email = binding.etEmail.text.toString(),
                    name = binding.etName.text.toString(),
                    surname = binding.etSurname.text.toString(),
                    patronymic = binding.etPatronymic.text.toString(),
                    phoneNumber = binding.etPhohe.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            } else {
                viewModel.registerAccount(
                    email = binding.etEmail.text.toString(),
                    name = binding.etName.text.toString(),
                    surname = binding.etSurname.text.toString(),
                    patronymic = binding.etPatronymic.text.toString(),
                    phoneNumber = binding.etPhohe.text.toString(),
                    password = binding.etPassword.text.toString()
                )
            }
        }
        binding.authButton.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_authorizationFragment)
        }
    }

    override fun onObserveData() {
        viewModel.getRegistrationAccountState().observeFlow(this) { registeredUser ->
            if (registeredUser.registrationModel.succeded
                && registeredUser.loginModel.succeeded
            ) {
                showMessage(getString(UiCoreStrings.register_success_message))
                findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
            }
        }
        viewModel.getRegistrationStudentState().observeFlow(this) { registeredStudent ->
            if (registeredStudent.succeeded) {
                showMessage(getString(UiCoreStrings.register_success_message))
                findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
            }
        }
        viewModel.getResultNotSuccessFlow().observeFlow(this) { errorText ->
            showMessage(errorText)
        }
    }
}
