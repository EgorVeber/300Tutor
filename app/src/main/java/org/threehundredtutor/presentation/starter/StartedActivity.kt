package org.threehundredtutor.presentation.starter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import org.threehundredtutor.R
import org.threehundredtutor.databinding.StartedActivityBinding
import org.threehundredtutor.di.starter.StartedComponent

class StartedActivity : AppCompatActivity(), BottomNavigationVisibility {

    private lateinit var binding: StartedActivityBinding

    private val startedComponent by lazy {
        StartedComponent.createStartedComponent()
    }

    val viewModel by viewModels<StarterViewModel> {
        startedComponent.viewModelMapFactory()
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
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                }

                R.id.solutionHistoryFragment -> {
                    navController.navigate(R.id.solutionHistoryFragment)
                }

                R.id.accountFragment -> {
                    // TODO TutorAndroid-41 Добавить меню фрагмент а аккаунт фрагмекнт внутрь
                    navController.navigate(R.id.accountFragment)
                }
            }
            true
        }
        binding.mainBottomNavigation.setOnItemReselectedListener {}
    }

    override fun visibility(show: Boolean) {
        binding.mainBottomNavigation.isVisible = show
    }
}
