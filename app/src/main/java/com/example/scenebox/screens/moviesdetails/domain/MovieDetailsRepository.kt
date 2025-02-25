package com.example.scenebox.screens.moviesdetails.domain

interface MovieDetailsRepository {
    suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse
}
