package org.threehundredtutor.presentation.starter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Fade
import androidx.transition.TransitionManager.beginDelayedTransition
import androidx.transition.TransitionSet
import org.threehundredtutor.R
import org.threehundredtutor.databinding.StartedActivityBinding
import org.threehundredtutor.di.activity.ActivityComponent
import org.threehundredtutor.ui_common.fragment.BottomNavigationVisibility

class StartedActivity : AppCompatActivity(), BottomNavigationVisibility {

    private lateinit var binding: StartedActivityBinding

    private val activityComponent by lazy {
        ActivityComponent.createActivityComponent()
    }

    val viewModel by viewModels<ActivityViewModel> {
        activityComponent.viewModelMapFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(viewModel.onCreateActivity())
        binding = StartedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        binding.mainBottomNavigation.setupWithNavController(navController)

        binding.mainBottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.mainFragment -> {
                    navController.navigate(R.id.mainFragment)
                }

                R.id.solutionHistoryFragment -> {
                    navController.navigate(R.id.solutionHistoryFragment)
                }

                R.id.mainMenuFragment -> {
                    navController.navigate(R.id.mainMenuFragment)
                }
            }
            true
        }
        binding.mainBottomNavigation.setOnItemReselectedListener {}
    }

    override fun visibility(show: Boolean) {
        TransitionSet().also { transition ->
            transition.addTransition(Fade(Fade.IN))
            transition.addTransition(Fade(Fade.OUT))
            beginDelayedTransition(binding.mainBottomNavigation, transition)
        }
        binding.mainBottomNavigation.isVisible = show
    }
}
