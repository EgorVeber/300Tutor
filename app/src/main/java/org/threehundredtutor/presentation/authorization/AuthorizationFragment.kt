package org.threehundredtutor.presentation.authorization

import android.annotation.SuppressLint
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
import org.threehundredtutor.presentation.main_menu.adapter.BannerItem
import org.threehundredtutor.presentation.main_menu.adapter.PhotoManager
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

    private val delegateAdapter: PhotoManager = PhotoManager(
        mainMenuUiItemClickListener = { }
    )

    override fun onInitView(savedInstanceState: Bundle?) = with(binding) {
        binding.mainRecycler.adapter = delegateAdapter

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

        delegateAdapter.items = getItemsBanner()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getItemsBanner(): List<BannerItem> {
        val mutablLIst = mutableListOf<BannerItem>()
        var index = 2
        repeat(2) {
            with(mutablLIst) {
                add(BannerItem(name = "11", path = banner8, index = index))
                add(BannerItem(name = "1", path = banner1, index = index))
                add(BannerItem(name = "9", path = banner9, index = index))
                add(BannerItem(name = "10", path = banner10, index = index))
                add(BannerItem(name = "2", path = banner2, index = index))
                add(BannerItem(name = "5", path = banner5, index = index))
                add(BannerItem(name = "6", path = banner6, index = index))
                add(BannerItem(name = "7", path = banner7, index = index))
                add(
                    BannerItem(
                        name = "23213",
                        path = "https://cdn-icons-png.flaticon.com/512/2088/2088617.png",
                        index = index
                    )
                )
            }
            index = 1
        }
        with(mutablLIst) {
            add(BannerItem(name = "11", path = banner8, index = 4))
            add(BannerItem(name = "1", path = banner1, index = 4))
            add(BannerItem(name = "9", path = banner9, index = 4))
            add(BannerItem(name = "10", path = banner10, index = 4))
            add(BannerItem(name = "2", path = banner2, index = 4))
            add(BannerItem(name = "5", path = banner5, index = 4))
            add(BannerItem(name = "6", path = banner6, index = 4))
            add(BannerItem(name = "7", path = banner7, index = 4))
            add(
                BannerItem(
                    name = "23213",
                    path = "https://cdn-icons-png.flaticon.com/512/2088/2088617.png",
                    index = 4
                )
            )
        }
        return mutablLIst
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

    companion object {
        val banner1 =
            "https://img.freepik.com/free-photo/surprised-and-excited-happy-girl-unwrapping-gifts-on-christmas-eve-new-year-celebration-smiling-amus_1258-127766.jpg?w=2000&t=st=1724526727~exp=1724527327~hmac=a2e002da5fba1a85907c704ed68735e5b46ffedd3f8c0973a0532dc1ec6d7f38"
        val banner2 =
            "https://img.freepik.com/free-photo/be-my-valentine-lovely-gorgeous-redhead-curly-silly-girlfriend-holding-large-heart-sign-and-smiling_1258-127754.jpg?w=2000&t=st=1724526729~exp=1724527329~hmac=caf5adad8d30e1fe1774a82698450f8883d7faa96b7c31b08c030905c6aac0b4"

        val banner5 =
            "https://previews.123rf.com/images/nataliagesto/nataliagesto1203/nataliagesto120300297/12622565-%D0%B4%D0%BB%D0%B8%D0%BD%D0%BD%D1%8B%D0%B5-%D0%B1%D0%BE%D0%BB%D1%8C%D1%88%D0%B8%D0%B5-%D0%B1%D0%B0%D0%BD%D0%BD%D0%B5%D1%80-%D0%B1%D0%B0%D0%BD%D0%BD%D0%B5%D1%80%D1%8B-spalshing-%D0%B7%D0%B0%D1%81%D1%82%D0%B0%D0%B2%D0%BA%D0%B8-%D0%B3%D0%BE%D0%BB%D1%83%D0%B1%D1%8B%D0%B5-%D1%81%D0%B8%D0%BD%D0%B8%D0%B5-%D0%BA%D0%B0%D0%BF%D0%BB%D0%B8-%D0%B2%D0%BE%D0%B4%D0%B0-%D1%84%D0%BE%D0%BD%D1%8B.jpg"
        val banner6 =
            "https://img.freepik.com/premium-photo/two-small-schoolchildren-background-with-symbols-letters-created-with-generative-ai-technol_185193-163775.jpg?w=2000"
        val banner7 =
            "https://img.freepik.com/free-photo/portrait-happy-ginger-red-hair-girl-with-freckles-smiling-looking-camera-pastel-blue-backgroun_1258-118757.jpg?t=st=1724533674~exp=1724537274~hmac=5dd056aa2677848d7b9fe066b21d0cac9a11b01212cc9680502cc2aea02f122f&w=2000"
        val banner8 =
            "https://img.freepik.com/free-photo/girl-hearing-ridiculous-stupid-question-careless-unbothered-cute-redhead-woman-yellow-sweater_1258-126189.jpg?t=st=1724709836~exp=1724713436~hmac=a4b06811b099a9f6e8cd37c9d8a21526b1ed5d50967b6324c77be12c3d53d0bd&w=2000"
        val banner9 =
            "https://img.freepik.com/free-photo/vertical-shot-happy-korean-woman-medical-mask-holding-flight-tickets-passport-standing-wit_1258-140592.jpg?t=st=1724709905~exp=1724713505~hmac=97f1310659c2c89fc9f7cbed455cf742099890cf4867b22938b4056763108715&w=2000"
        val banner10 =
            "https://img.freepik.com/premium-psd/3d-illustration-cartoon-character-beautiful-girl-left-pointing_358001-2066.jpg?w=2000"

    }
}
