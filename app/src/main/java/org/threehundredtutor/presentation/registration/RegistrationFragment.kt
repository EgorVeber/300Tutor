package org.threehundredtutor.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.databinding.RegistrationFragmentBinding
import org.threehundredtutor.di.registration.RegistrationComponent
import org.threehundredtutor.presentation.registration.viewmodel.RegistrationViewModel

class RegistrationFragment : BaseFragment(R.layout.registration_fragment) {

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

    override fun onInitView() {
        binding.buttonRegister.setOnClickListener {
            val passwordText = binding.etPassword.text.toString()
            if (passwordText.length < 6 || passwordText.contains("[0-9]".toRegex()).not()) {
                showMessage(getString(R.string.password_wrong))
                return@setOnClickListener
            }
            if (binding.etEmail.text.toString().contains("@").not()) {
                showMessage(getString(R.string.email_wrong))
                return@setOnClickListener
            }
            if (binding.etPhohe.text.toString().isEmpty()) {
                showMessage(getString(R.string.phone_wrong))
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
                showMessage(getString(R.string.register_success_message))
                findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
            }
        }
        viewModel.getRegistrationStudentState().observeFlow(this) { registeredStudent ->
            if (registeredStudent.succeded) {
                showMessage(getString(R.string.register_success_message))
                findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
            }
        }
        viewModel.getResultNotSuccededFlow().observeFlow(this) { errorText ->
            showMessage(errorText)
        }
    }
}
