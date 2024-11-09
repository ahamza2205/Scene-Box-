package com.example.scenebox.screens.moviesdetails.domain

import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend fun invoke(movieId: Int): MovieDetailsResponse {
        return movieDetailsRepository.getMovieDetails(movieId)
    }
}
