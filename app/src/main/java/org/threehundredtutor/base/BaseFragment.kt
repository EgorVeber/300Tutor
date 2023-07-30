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

abstract class BaseFragment(@LayoutRes layoutResourceId: Int) : Fragment(layoutResourceId) {

    abstract val viewModel: BaseViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("onCreate", "Created screen ${this::class.java.name}")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onObserveError()
    }

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
