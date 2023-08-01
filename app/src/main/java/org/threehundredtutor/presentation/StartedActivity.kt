package org.threehundredtutor.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.threehundredtutor.common.PrefsSettings
import org.threehundredtutor.databinding.StartedActivityBinding

class StartedActivity : AppCompatActivity() {
    private val prefsSettings by lazy { PrefsSettings }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(prefsSettings.getThemePrefs())
        val binding = StartedActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
