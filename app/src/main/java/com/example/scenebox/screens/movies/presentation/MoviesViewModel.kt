    package com.example.scenebox.screens.movies.presentation

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import androidx.paging.PagingData
    import androidx.paging.cachedIn
    import com.example.scenebox.screens.movies.domain.GetMoviesUseCase
    import com.example.scenebox.screens.movies.domain.Movie
    import com.example.scenebox.screens.movies.domain.MovieRepository
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.flow.asStateFlow
    import kotlinx.coroutines.flow.collectLatest
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class MoviesViewModel @Inject constructor(
        private val getMoviesUseCase: GetMoviesUseCase
    ) : ViewModel() {

        private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
        val movies: StateFlow<PagingData<Movie>> = _movies.asStateFlow()

        fun updateMoviesList(selectedTab: String, apiKey: String, minRating: Double = 0.0, genre: String? = null) {
            viewModelScope.launch {
                getMoviesUseCase(selectedTab, apiKey, minRating, genre)
                    .cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _movies.value = pagingData
                    }
            }
        }
    }
