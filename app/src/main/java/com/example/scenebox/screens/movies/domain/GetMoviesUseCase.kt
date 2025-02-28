package com.example.scenebox.screens.movies.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(category: String, apiKey: String): Flow<PagingData<Movie>> {
        return repository.getMovies(category, apiKey)
    }
}
