package org.threehundredtutor.ui_common.fragment.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.threehundredtutor.ui_common.UiCoreStrings
import org.threehundredtutor.ui_common.flow.observeFlow
import org.threehundredtutor.ui_common.fragment.BottomNavigationVisibility
import org.threehundredtutor.ui_common.fragment.addBackPressedCallback
import org.threehundredtutor.ui_common.fragment.addBackPressedDelayCallback
import org.threehundredtutor.ui_common.fragment.showMessage

abstract class BaseFragment(@LayoutRes layoutResourceId: Int) : Fragment(layoutResourceId),
    BackPressed {

    abstract val viewModel: BaseViewModel

    open val bottomMenuVisible: Boolean = true
    open var customHandlerBackStack: Boolean = false
    open var customHandlerBackStackWithDelay: Boolean = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInject()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "Created screen ${this::class.java.name}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitView(savedInstanceState)
        onObserveData()
        onObserveError()
        if (customHandlerBackStackWithDelay) {
            addBackPressedDelayCallback()
        } else if (customHandlerBackStack) {
            addBackPressedCallback { onBackPressed() }
        }
    }

    override fun onBackPressed() {}

    protected open fun onInject() {}

    override fun onStart() {
        (requireActivity() as BottomNavigationVisibility).visibility(bottomMenuVisible)
        super.onStart()
    }

    protected open fun onInitView(savedInstanceState: Bundle?) {}

    protected open fun onObserveData() {}
    protected open fun onObserveError() {
        viewModel.getErrorState().observeFlow(this) { errorState ->
            onError(errorState)
        }
    }

    private fun onError(error: BaseViewModel.ErrorState) {
        when (error) {
            is BaseViewModel.ErrorState.WrongData -> {
                showErrorToast(getString(UiCoreStrings.wrong_data_error_message))
            }

            is BaseViewModel.ErrorState.UserUnathorized -> {
                showErrorToast(getString(UiCoreStrings.unathorized_error_message))
            }

            is BaseViewModel.ErrorState.ServerError -> {
                showErrorToast(getString(UiCoreStrings.server_error_message))
            }

            is BaseViewModel.ErrorState.SomethingWrongError -> {
                showErrorToast(getString(UiCoreStrings.unknown_error_message))
            }

            is BaseViewModel.ErrorState.NoError -> {}
        }
    }

    private fun showErrorToast(toastText: String) {
        showMessage(toastText)
        viewModel.updateState()
    }
}

