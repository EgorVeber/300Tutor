package org.threehundredtutor.presentation.starter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import org.threehundredtutor.databinding.StartedActivityBinding
import org.threehundredtutor.di.starter.StartedComponent

class StartedActivity : AppCompatActivity() {
    private val startedComponent by lazy {
        StartedComponent.createStartedComponent()
    }

    val viewModel by viewModels<StarterViewModel> {
        startedComponent.viewModelMapFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(viewModel.onCreateActivity())
        val binding = StartedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
