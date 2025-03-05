package com.example.scenebox.screens.movies.domain


import androidx.paging.PagingData
import androidx.paging.filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    operator fun invoke(category: String, apiKey: String, minRating: Double = 0.0, genreId: Int? = null): Flow<PagingData<Movie>> {
        return repository.getMovies(category, apiKey).map { pagingData ->
            pagingData.filter { movie ->
                movie.vote_average >= minRating &&
                        (genreId == null || movie.genre_ids.contains(genreId))
            }
        }
    }
}