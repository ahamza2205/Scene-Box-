package com.example.scenebox.screens.movies.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scenebox.screens.movies.domain.Movie
import com.example.scenebox.screens.movies.domain.GetNowPlayingMoviesUseCase
import com.example.scenebox.screens.movies.domain.GetPopularMoviesUesCase
import com.example.scenebox.screens.movies.domain.GetTopRatedMoviesUseCase
import com.example.scenebox.screens.movies.domain.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getPopularMoviesUesCase: GetPopularMoviesUesCase,
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {
    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> get() = _movies
    fun updateMoviesList(selectedTab: String, apiKey: String) {
        viewModelScope.launch {
            _movies.value = when (selectedTab) {
                "Now Playing" -> getNowPlayingMoviesUseCase(apiKey)
                "Popular" -> getPopularMoviesUesCase(apiKey)
                "Upcoming" -> getUpcomingMoviesUseCase(apiKey)
                "Top Rated" -> getTopRatedMoviesUseCase(apiKey)
                else -> emptyList()
            }
        }
    }
}

