package com.example.scenebox

import com.example.scenebox.BottomBar.MainScreenWithAnchoredBottomBar
import SceneBoxTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.scenebox.managers.LocaleManager
import com.example.scenebox.sharedPreferences.PreferencesManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencesManager = PreferencesManager(this)
        LocaleManager.setLocale(this, preferencesManager.getSavedLanguage())

        setContent {
            SceneBoxTheme {
                MainScreenWithAnchoredBottomBar()
            }
        }
    }

}

