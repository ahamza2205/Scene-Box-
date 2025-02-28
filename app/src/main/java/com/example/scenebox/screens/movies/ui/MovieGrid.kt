package com.example.scenebox.screens.movies.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.LazyPagingItems
import kotlinx.coroutines.flow.Flow
import com.example.scenebox.screens.movies.domain.Movie

@Composable
fun MovieGrid(moviesFlow: Flow<PagingData<Movie>>, onMovieClick: (Int) -> Unit , modifier: Modifier = Modifier) {
    val lazyMovies: LazyPagingItems<Movie> = moviesFlow.collectAsLazyPagingItems()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(lazyMovies.itemCount) { index ->
            val movie = lazyMovies[index]
            if (movie != null) {
                MovieCard(movie = movie, onMovieClick = onMovieClick)
            }
        }

        item(span = { GridItemSpan(2) }) {
            if (lazyMovies.loadState.append is androidx.paging.LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}






