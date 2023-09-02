package org.threehundredtutor.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import org.threehundredtutor.R
import org.threehundredtutor.base.BaseFragment
import org.threehundredtutor.base.BaseViewModel
import org.threehundredtutor.common.extentions.navigate
import org.threehundredtutor.databinding.HomeFragmentBinding

class HomeFragment : BaseFragment(R.layout.home_fragment) {
    override val viewModel: BaseViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeFragmentBinding.bind(view)

        binding.mainBottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.homeMenuItem -> {

                }

                R.id.testSolvingMenuItem -> {

                }

                R.id.profileMenuItem -> {
                    this.navigate(R.id.action_homeFragment_to_accountFragment)
                }
            }
            true
        }
        binding.mainBottomNavigation.setOnItemReselectedListener {}
    }

    override fun onBackPressed() {
        findNavController().navigateUp()
    }
}
