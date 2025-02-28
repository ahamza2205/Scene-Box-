package com.example.scenebox

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.scenebox.data.remote.MovieApiService
import com.example.scenebox.screens.movies.domain.Movie
import kotlinx.coroutines.delay

class MoviesPagingSource(
    private val apiService: MovieApiService,
    private val category: String,
    private val apiKey: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val pageSize = 20
            delay(2000)

            val response = when (category) {
                "Now Playing" -> apiService.getNowPlayingMovies(apiKey, page = currentPage)
                "Popular" -> apiService.getPopularMovies(apiKey, page = currentPage)
                "Upcoming" -> apiService.getUpcomingMovies(apiKey, page = currentPage)
                "Top Rated" -> apiService.getTopRatedMovies(apiKey, page = currentPage)
                else -> throw IllegalArgumentException("Invalid category")
            }
            val movies = response.results ?: emptyList()

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

