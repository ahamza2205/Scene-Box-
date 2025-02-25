package com.example.scenebox.screens.moviesdetails.data

import com.example.scenebox.data.remote.MovieApiService
import com.example.scenebox.screens.moviesdetails.domain.MovieDetailsRepository
import com.example.scenebox.screens.moviesdetails.domain.MovieDetailsResponse

import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsResponse {
        return movieApiService.getMovieDetails(movieId, "bc25642cb67605dd1a496763099996a5")
    }
}
