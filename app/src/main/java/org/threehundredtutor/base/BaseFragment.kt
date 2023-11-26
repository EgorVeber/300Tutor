package org.threehundredtutor.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import org.threehundredtutor.R
import org.threehundredtutor.common.extentions.observeFlow
import org.threehundredtutor.common.extentions.showMessage
import org.threehundredtutor.common.utils.BackPressed
import org.threehundredtutor.common.viewcopmponents.addBackPressedCallback
import org.threehundredtutor.common.viewcopmponents.addBackPressedDelayCallback
import org.threehundredtutor.presentation.starter.BottomNavigationVisibility

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
                showErrorToast(getString(R.string.wrong_data_error_message))
            }

            is BaseViewModel.ErrorState.UserUnathorized -> {
                showErrorToast(getString(R.string.unathorized_error_message))
            }

            is BaseViewModel.ErrorState.ServerError -> {
                showErrorToast(getString(R.string.server_error_message))
            }

            is BaseViewModel.ErrorState.SomethingWrongError -> {
                showErrorToast(getString(R.string.unknown_error_message))
            }

            else -> {
                showErrorToast(getString(R.string.unknown_error_message))
            }
        }
    }

    private fun showErrorToast(toastText: String) {
        showMessage(toastText)
    }
}
