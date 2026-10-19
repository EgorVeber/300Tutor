package com.v_points.app.presentation.registration

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.v_points.app.R
import com.v_points.app.core.UiCoreLayout
import com.v_points.app.core.UiCoreStrings
import com.v_points.app.di.registration.RegistrationComponent
import com.v_points.app.presentation.registration.viewmodel.RegistrationViewModel
import com.v_points.app.ui_common.flow.observeFlow
import com.v_points.app.ui_common.fragment.base.BaseFragment
import com.v_points.app.ui_common.fragment.showMessage
import com.v_points.app.ui_core.databinding.RegistrationFragmentBinding

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

            viewModel.registerStudent(
                email = binding.etEmail.text.toString(),
                name = binding.etName.text.toString(),
                surname = binding.etSurname.text.toString(),
                phoneNumber = binding.etPhohe.text.toString(),
                password = binding.etPassword.text.toString()
            )
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
