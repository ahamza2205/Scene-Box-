    package com.example.scenebox.screens.movies.presentation

    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import androidx.paging.PagingData
    import androidx.paging.cachedIn
    import com.example.scenebox.BuildConfig
    import com.example.scenebox.screens.movies.domain.GetMoviesUseCase
    import com.example.scenebox.screens.movies.domain.Movie
    import com.example.scenebox.sharedPreferences.PreferencesManager
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.flow.asStateFlow
    import kotlinx.coroutines.flow.collectLatest
    import kotlinx.coroutines.launch
    import javax.inject.Inject

    @HiltViewModel
    class MoviesViewModel @Inject constructor(
        private val getMoviesUseCase: GetMoviesUseCase,
        private val preferencesManager: PreferencesManager
    ) : ViewModel() {

        private var currentTab: String = "Now Playing"


        private val _movies = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
        val movies: StateFlow<PagingData<Movie>> = _movies.asStateFlow()

        private val _selectedLanguage = MutableStateFlow(preferencesManager.getSavedLanguage())

        init {
            observeLanguageChanges()
        }

        fun updateMoviesList(
            selectedTab: String,
            apiKey: String,
            minRating: Double = 0.0,
            genreId: Int? = null
        ) {
            viewModelScope.launch {
                getMoviesUseCase(selectedTab, apiKey, minRating, genreId)
                    .cachedIn(viewModelScope)
                    .collectLatest { pagingData ->
                        _movies.value = pagingData
                    }
            }
        }


        private fun observeLanguageChanges() {
            viewModelScope.launch {
                preferencesManager.languageFlow.collectLatest { newLanguage ->
                    _selectedLanguage.value = newLanguage
                    updateMoviesList(
                        selectedTab = currentTab,
                        apiKey = BuildConfig.API_KEY
                    )
                }
            }
        }
    }


