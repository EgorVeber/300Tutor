package org.threehundredtutor.presentation.authorization

import android.content.Context
import android.graphics.PointF
import android.os.Bundle
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.threehundredtutor.R
import org.threehundredtutor.core.UiCoreLayout
import org.threehundredtutor.core.navigate
import org.threehundredtutor.di.authorization.AuthorizationComponent
import org.threehundredtutor.presentation.main_menu.adapter.ImageItem
import org.threehundredtutor.presentation.main_menu.adapter.ImageManager
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

    private val imageManager by lazy {
        ImageManager()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = AuthorizationFragmentBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onInitView(savedInstanceState: Bundle?) = with(binding) {
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
        binding.recyclerImages.adapter = imageManager
    }

    override fun onObserveData() {

        val list = getImages()
        val gifsList = getGifs()

        //imageManager.items = (gifsList+ getImages()).shuffled()
        imageManager.items = list

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
        // binding.recyclerImages.setLayoutManager(StaggeredGridLayoutManager(2, 1))
    }

    private fun getGifs(): List<ImageItem> {
        return gifsStatickList.map {
            ImageItem(it.substring(0, 20), it)
        }
    }

    private val gifsStatickList =
        listOf(
            "https://i.giphy.com/W2Utw2mUvqCdyS59wB.webp",
            "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExc3dkMGkxZDI2N2J0Ynpyc3Z2cGY0Y2ExMGs3dXp1NTQyYnZzemI1ayZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/HsEGrnQRSHb5pZezKm/giphy.gif",
            "https://i.giphy.com/8cOkSOuvIChHNYOyP7.webp",
            "https://i.giphy.com/TrgAAwTiTfHhhH40jJ.webp",
            "https://i.giphy.com/Z5xk7fGO5FjjTElnpT.webp",
            "https://i.giphy.com/G7nUzRr3LvSu48bR8U.webp",
            "https://i.giphy.com/1oSzUajJd2DDLhmbzL.webp",
            "https://i.giphy.com/l396O8kn1qbPcb4ha.webp",
            "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExdjVyYXI3MnFmZTVlcGdxcG9vY2dhMjBybzNrNHliZW1iamg1Nmg3ZiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/8KnC7pRaLbpq315GZG/giphy.gif",
            "https://i.giphy.com/MUHNdrm3vk7MoyUsCO.webp",
            "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExN2V3NWpkcG9xamNlZ2xhb29jZjU5MzFuOGljbWxuOXJtZnZmeDJ1ZyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/XqIyMEeqmdo6ZaMdJW/giphy.gif",
            "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExcnVrcjQ1NDE3YnJxYnE3OXY3aTliMTU1dXV5aHJtc2MxbDR0b2w0aSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/CHsNeh1fRpYB86yEhr/giphy.gif",
            "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExNTdrcHdhemM1a3g4b2ExZnlvdGh4ajJ1eGhnaGQ4OXF1ZG5wMDR1NSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/wH8s0Ntwgh5YI/giphy.gif",
            "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExZzdxdDUxZnJ5NHo1dWd2ZDNhandsMjM0emQ1ZXMxOW8yYmptZzVkMSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/kaMwwM91UCxstRfvA3/giphy.gif",
            "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExNTQ5d2Z1dmU0M2VuNHRobDZ2ZmlsNDY4Y25yNDA5Z2d4bmZ4OTN4aSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/YUG6QfNKphYLIMfLmx/giphy.gif",
            "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExaHlpYzFnbzB6aWg1aWpiaXRzbzA1eGhwbTV6eTM5NXYyemljaDUweiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/eNP3TzT29nxlkPdHrh/giphy.gif",
            "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExbmN4aDQ3NHliaGQ2bzZ5dWVkMml0OHlhYjhuN3Nzemxkd3U1ZTIyNSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/F6PFPjc3K0CPe/giphy.gif",
            "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExOHdyZmdwOWptY2htYzdzaWVqd21vanhkaWdhZTNwODVkdGRwcHB3dyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/d5w7LsjZbqN5S9rne7/giphy.gif",
            "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExbW55NXpoOWhmZWl4aXRwY2Jzd3hrdGFpY3Buc3dwaXA1cXA3d3ZxcSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/oUS6u2rbjg4JD4Z9Lp/giphy.gif",
            "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExMnp0bjAyNGdxNDN4M3drcHF1eG8wNmJ0Y20wbTdyZTl6eG1odWl5NiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/kYNVwkyB3jkauFJrZA/giphy.gif",
            "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExZzdicWVnMjE4aDF0anpvOWgwdTJ0YW81eXM4NmVuYmQxejFvMWpsYiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/M4cFbuylj8olUdDGkr/giphy.gif",
            "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExZzRla205dDY0ZjF5eXF4cmh2cDNxM20yY3pzdmdkYzUxc2hzZW1obiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/dwI09ZWZ93gIt7uhQm/giphy.gif",
            "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExYWtxOGlndXAzbW96d3Rvb21jYWIzMTdkcnJreG1rMm9uZXZ2dTczaCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/6vg3kRY0Jk3Go/giphy.gif",
            "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExOWhyYWNvN241em02Nms3ZnJha3hrc2VsMDdwZHRsb3drMTAzZWFkdyZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/rpouVO5Coq6SCUS7dB/giphy.gif",
            "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExajFlN2I5ZWZhcmc3eHF6NDlzb2hod3FlMWtycjhxMXJjeHZxYzJ5YSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/kwSZzHYRwd4Lm/giphy.gif",
            "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExZWtsdWR2YXR3NXlnNjV6YXhoemJ4MTJxMThhajR3aG1vNWRmYjYzOCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/sVbRvsU5XHc0YMGtTs/giphy.gif",
        )

    private fun getImages(): List<ImageItem> {
        val list = listOf(
            ImageItem("asd", "1152"),
            ImageItem("2", "1150"),
            ImageItem("3", "1175"),
            ImageItem("4", "1173"),
            ImageItem("5", "1148"),
            ImageItem("71", "1153"),
            ImageItem(name = "5466", "1151"),
            ImageItem("45645", "1150"),
            ImageItem("4564", "1167"),
            ImageItem("10", "1089"),
            ImageItem("4564", "376"),
            ImageItem("234", "901"),
            ImageItem("23423", "624"),
            ImageItem("34", "440"),
            ImageItem("241", "1140"),
            ImageItem("341", "49"),
            ImageItem("134234", "1209"),
            ImageItem("12312", "27"),
            ImageItem("sdas", "311"),
            ImageItem(name = "asd", path = "1208"),
            ImageItem("asd", "145")
        )
        return list
    }

    private fun showInputType(visible: Boolean) {

    }
}


class SpeedyLinearLayoutManager : LinearLayoutManager {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        return super.scrollVerticallyBy(dy, recycler, state)
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        state: RecyclerView.State,
        position: Int
    ) {
        val linearSmoothScroller: LinearSmoothScroller =
            object : LinearSmoothScroller(recyclerView.context) {
                override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                    return super.computeScrollVectorForPosition(targetPosition)
                }

                override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                    return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
                }
            }
        linearSmoothScroller.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }

    companion object {
        private const val MILLISECONDS_PER_INCH = 100f //default is 25f (bigger = slower)
    }
}