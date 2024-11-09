package com.example.scenebox.screens.moviesdetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scenebox.screens.moviesdetails.domain.GetMovieDetailsUseCase
import com.example.scenebox.screens.moviesdetails.domain.MovieDetailsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {
    private val _movieDetails = MutableStateFlow<MovieDetailsResponse?>(null)
    val movieDetails: StateFlow<MovieDetailsResponse?> = _movieDetails
    private val _errorState = MutableStateFlow<String?>("")
    val errorState: StateFlow<String?> = _errorState
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val details = getMovieDetailsUseCase.invoke(movieId)
                _movieDetails.emit(details)
                _errorState.emit("")
            } catch (e: Exception) {
                _errorState.emit("Failed to load movie details.")
            }
        }
    }
}




