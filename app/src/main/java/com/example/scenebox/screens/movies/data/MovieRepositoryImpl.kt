package com.example.scenebox.screens.movies.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.scenebox.data.remote.MovieApiService
import com.example.scenebox.pagination.MoviesPagingSource
import com.example.scenebox.screens.movies.domain.Movie
import com.example.scenebox.screens.movies.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: MovieApiService) :
    MovieRepository {

    override fun getMovies(category: String, apiKey: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = { MoviesPagingSource(apiService, category, apiKey) }
        ).flow
    }
}
