package com.example.scenebox.screens.movies.presentation

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.scenebox.screens.movies.ui.MovieGrid
import com.example.scenebox.screens.movies.ui.TabRow

@Composable
fun MoviesScreen(viewModel: MoviesViewModel = hiltViewModel()) {
    var selectedTab by remember { mutableStateOf("Now Playing") }
    val movies by viewModel.movies.collectAsState()
    LaunchedEffect(selectedTab) {
        viewModel.updateMoviesList(selectedTab, "bc25642cb67605dd1a496763099996a5")
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            GradientText(text = "Movies List")
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Discover, Watch, Enjoy!",
                style = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.Light,
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier.align(Alignment.Start)
            )
        }
        TabRow(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        MovieGrid(movies = movies)
    }
}



