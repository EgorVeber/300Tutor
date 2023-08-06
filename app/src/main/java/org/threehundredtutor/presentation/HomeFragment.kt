package org.threehundredtutor.presentation

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.base.BaseViewModel

class HomeFragment : BaseFragment(R.layout.home_fragment){
    override val viewModel: BaseViewModel by viewModels()

    override fun onBackPressed() {
        findNavController().navigateUp()
    }
}
