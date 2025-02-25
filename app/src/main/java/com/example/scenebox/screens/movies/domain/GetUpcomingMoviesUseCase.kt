package com.example.scenebox.screens.movies.domain

import javax.inject.Inject

class GetUpcomingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(apiKey: String): List<Movie> {
        return repository.getUpcomingMovies(apiKey)
    }
}