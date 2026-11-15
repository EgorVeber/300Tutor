package com.v_points.app.presentation.starter

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.transition.Fade
import androidx.transition.TransitionManager.beginDelayedTransition
import androidx.transition.TransitionSet
import com.v_points.app.R
import com.v_points.app.databinding.StartedActivityBinding
import com.v_points.app.di.activity.ActivityComponent
import com.v_points.app.ui_common.fragment.BottomNavigationVisibility

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
        enableEdgeToEdge()
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


    private fun setupWhiteStatusBar(window: Window) {
        // 1. Устанавливаем белый цвет (работает на API 21+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.WHITE
        }

        // 2. Включаем тёмные иконки (работает на API 23+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Современный способ через WindowInsetsControllerCompat
            WindowCompat.getInsetsController(window, window.decorView)
                .isAppearanceLightStatusBars = true
        } else {
            // Запасной вариант для старых версий (API 21-22)
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
