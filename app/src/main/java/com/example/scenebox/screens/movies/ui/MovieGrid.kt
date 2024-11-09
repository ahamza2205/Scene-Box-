package com.example.scenebox.screens.movies.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scenebox.screens.movies.domain.Movie

@Composable
fun MovieGrid(movies: List<Movie>, onMovieClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(movies.size) { index ->
            val movie = movies[index]
            MovieCard(movie = movie, onMovieClick = onMovieClick)
        }
    }
}
