package com.example.scenebox.screens.movies.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.scenebox.screens.movies.domain.Movie
import coil.compose.rememberImagePainter

@Composable
fun MovieCard(movie: Movie, onMovieClick: (Int) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onMovieClick(movie.id) } // Add clickable here
    ) {
        Image(
            painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${movie.poster_path}"),
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

