package com.example.scenebox

import com.example.scenebox.BottomBar.MainScreenWithAnchoredBottomBar
import SceneBoxTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SceneBoxTheme {
                MainScreenWithAnchoredBottomBar()
            }
        }
    }
}

