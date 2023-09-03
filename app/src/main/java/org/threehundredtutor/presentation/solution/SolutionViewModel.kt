package org.threehundredtutor.presentation.solution

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.launchJob
import org.threehundredtutor.data.solution.TestSolutionQueryResponse
import org.threehundredtutor.domain.solution.GetSolutionUseCase
import javax.inject.Inject

class SolutionViewModel @Inject constructor(
    private val getSolutionUseCase: GetSolutionUseCase
) : BaseViewModel() {

    private val loadingState = MutableStateFlow(false)

    init {
        viewModelScope.launchJob(tryBlock = {
            val mainModel: TestSolutionQueryResponse = getSolutionUseCase.invoke(CURRENT_SOLUTION_ID)

            Log.d("SolutionViewModel", "Solutioin" + mainModel.solution.toString())

        }, catchBlock = { throwable ->
            Log.d("getSolution", throwable.toString())
            handleError(throwable)
        })
    }

    companion object {
        const val CURRENT_SOLUTION_ID = "04c0b9ef-408d-4179-98e2-9dd216f0db5a"
    }
}
