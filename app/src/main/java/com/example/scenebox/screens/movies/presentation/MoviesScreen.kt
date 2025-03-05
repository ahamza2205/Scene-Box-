    package com.example.scenebox.screens.movies.presentation

    import android.util.Log
    import androidx.compose.animation.AnimatedVisibility
    import androidx.compose.animation.core.tween
    import androidx.compose.animation.expandVertically
    import androidx.compose.animation.fadeIn
    import androidx.compose.animation.fadeOut
    import androidx.compose.animation.shrinkVertically
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import com.example.scenebox.ui.theme.GradientText
    import androidx.compose.foundation.layout.*
    import androidx.compose.material3.Button
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.material3.Text
    import androidx.compose.runtime.*
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.TextStyle
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.hilt.navigation.compose.hiltViewModel
    import androidx.paging.compose.collectAsLazyPagingItems
    import com.example.scenebox.BuildConfig
    import com.example.scenebox.screens.movies.ui.MovieGrid
    import com.example.scenebox.screens.movies.ui.TabRow

    @Composable
    fun MoviesScreen(
        selectedTab: String,
        onTabSelected: (String) -> Unit,
        onMovieClick: (Int) -> Unit,
        onFilterClick: () -> Unit,
        viewModel: MoviesViewModel = hiltViewModel()
    ) {
        val moviesFlow = viewModel.movies
        var showBottomSheet by remember { mutableStateOf(false) }

        val genres = listOf("Action", "Drama", "Comedy", "Horror", "Sci-Fi")
        var selectedGenres by remember { mutableStateOf(listOf<String>()) }
        var rating by remember { mutableStateOf(5f) }

        val onGenreSelected: (String) -> Unit = { genre ->
            selectedGenres = if (selectedGenres.contains(genre)) {
                selectedGenres - genre
            } else {
                selectedGenres + genre
            }
        }

        val onRatingChanged: (Float) -> Unit = { newRating ->
            rating = newRating
        }

        LaunchedEffect(selectedTab) {
            viewModel.updateMoviesList(selectedTab, BuildConfig.API_KEY)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    GradientText(text = "Movies List")
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Discover, Watch, Enjoy!",
                        style = TextStyle(
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
                Button(onClick = { showBottomSheet = true }) {
                    Text("Filter")
                }
            }
            TabRow(selectedTab = selectedTab, onTabSelected = onTabSelected)

            AnimatedVisibility(
                visible = true,
                enter = fadeIn(tween(1000)) + expandVertically(tween(1000)),
                exit = fadeOut(tween(1000)) + shrinkVertically(tween(1000))
            ) {
                MovieGrid(moviesFlow = moviesFlow, onMovieClick = onMovieClick)
            }
        }

        if (showBottomSheet) {
            FilterBottomSheet(
                genres = genres,
                selectedGenres = selectedGenres,
                onGenreSelected = onGenreSelected,
                rating = rating,
                onRatingChanged = onRatingChanged,
                onApplyFilter = {
                    val selectedGenreId = genreMap[selectedGenres.firstOrNull()]
                    Log.d("MoviesScreen", "Applying filter with Genre: $selectedGenres, GenreID: $selectedGenreId, MinRating: $rating")
                    viewModel.updateMoviesList(selectedTab, BuildConfig.API_KEY, rating.toDouble(), selectedGenreId)
                    showBottomSheet = false
                },
                onDismiss = { showBottomSheet = false }
            )

        }
    }
    val genreMap = mapOf(
        "Action" to 28,
        "Drama" to 18,
        "Comedy" to 35,
        "Horror" to 27,
        "Sci-Fi" to 878
    )
