package com.example.scenebox.screens.moviesdetails.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
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
import com.example.scenebox.screens.moviesdetails.domain.MovieDetailsResponse
import com.example.scenebox.screens.moviesdetails.ui.StarRatingBar

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    selectedTab: String,
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

            Image(
                painter = rememberImagePainter(data = "https://image.tmdb.org/t/p/w500${details.poster_path}"),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
            )

            MovieDetailsContent(
                details = details,
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Composable
fun MovieDetailsContent(
    details: MovieDetailsResponse,
    onBackClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        MovieDetailsHeader(
            title = details.title,
            releaseDate = details.release_date,
            onBackClick = onBackClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        MovieRatingAndFavorite(
            rating = details.vote_average,
            isFavorite = details.id == details.id,
            onFavoriteClick = onFavoriteClick
        )

        Spacer(modifier = Modifier.height(8.dp))

        MovieRuntime(runtime = details.runtime)

        Spacer(modifier = Modifier.height(16.dp))

        MovieOverview(overview = details.overview)

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun MovieDetailsHeader(
    title: String,
    releaseDate: String,
    onBackClick: () -> Unit
) {
    Column {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        Text(
            text = "$title (${releaseDate.take(4)})",
            color = Color.White,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun MovieRatingAndFavorite(
    rating: Double,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        StarRatingBar(userScore = (rating * 10).toInt())

        IconButton(onClick = onFavoriteClick) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorite",
                tint = Color.Red
            )
        }
    }
}

@Composable
fun MovieRuntime(runtime: Int) {
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
            text = "${runtime / 60}h ${runtime % 60}m",
            color = Color.White,
            style = MaterialTheme.typography.subtitle2
        )
    }
}

@Composable
fun MovieOverview(overview: String) {
    Column {
        Text(
            text = "Overview",
            color = Color.White,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = overview,
            color = Color.White,
            style = MaterialTheme.typography.body1
        )
    }
}
