package com.example.scenebox.screens.moviesdetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.scenebox.R
import com.example.scenebox.screens.moviesdetails.ui.StarRatingBar

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val movieDetails by viewModel.movieDetails.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        errorState?.let {
            Text(text = it, color = Color.Red)
        }

        movieDetails?.let { details ->
            // Image takes up the full screen
            Image(
                painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${details.poster_path}"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Background overlay to darken the image slightly
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Spacer to move content down
                Spacer(modifier = Modifier.height(40.dp))

                // Back button
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Movie Title
                Text(
                    text = "${details.title} (${details.release_date.take(4)})",
                    color = Color.White,
                    style = MaterialTheme.typography.h5
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Rating and Favorite Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StarRatingBar(userScore = (details.vote_average * 10).toInt())

                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (details.id == movieId) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = Color.Red
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Runtime
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.time),
                        contentDescription = "Runtime",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${details.runtime / 60}h ${details.runtime % 60}m",
                        color = Color.White,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Overview Title
                Text(
                    text = "Overview",
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                // Overview Content
                Text(
                    text = details.overview,
                    color = Color.White,
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}






