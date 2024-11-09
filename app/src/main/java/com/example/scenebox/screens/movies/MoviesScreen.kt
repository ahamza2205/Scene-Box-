package com.example.scenebox.screens.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.scenebox.ui.theme.GradientText
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MoviesScreen() {
    var selectedTab by remember { mutableStateOf("Now Playing") }

    Column(modifier = Modifier.fillMaxSize()) {
        // Top-left titles
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // "Movies List" with Gradient Text
            GradientText(text = "Movies List")

            Spacer(modifier = Modifier.height(4.dp))

            // "Discover, Watch, Enjoy!" in white color
            Text(
                text = "Discover, Watch, Enjoy!",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    color = Color.White // Set the color to white
                ),
                modifier = Modifier.align(Alignment.Start)
            )
        }

        // TabRow now displays tabs in a grid with 2 columns
        TabRow(selectedTab = selectedTab, onTabSelected = { selectedTab = it })

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            GradientText("Movies: $selectedTab")
        }
    }
}


