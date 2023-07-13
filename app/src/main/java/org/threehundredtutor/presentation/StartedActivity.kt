package org.threehundredtutor.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import org.threehundredtutor.R
import org.threehundredtutor.common.PrefsSettings
import org.threehundredtutor.databinding.StartedActivityLayoutBinding

class StartedActivity : AppCompatActivity() {
    private val prefsSettings by lazy { PrefsSettings }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(prefsSettings.getThemePrefs())
        val binding = StartedActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.commit { add(R.id.fragmentContainerMain, StartedFragment()) }
    }
}