package com.example.scenebox.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.scenebox.data.remote.MovieApiService
import com.example.scenebox.screens.movies.domain.Movie
import kotlinx.coroutines.delay

class MoviesPagingSource(
    private val apiService: MovieApiService, // API service to fetch movie data
    private val category: String, // Movie category (e.g., "Now Playing", "Popular")
    private val apiKey: String // API key for authentication
) : PagingSource<Int, Movie>() { // PagingSource requires a key type (Int) and data type (Movie)

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1 // Start from page 1 if no key is provided
            delay(2000) // Simulating a network delay of 2 seconds

            // Fetch data based on the selected category
            val response = when (category) {
                "Now Playing" -> apiService.getNowPlayingMovies(apiKey, page = currentPage)
                "Popular" -> apiService.getPopularMovies(apiKey, page = currentPage)
                "Upcoming" -> apiService.getUpcomingMovies(apiKey, page = currentPage)
                "Top Rated" -> apiService.getTopRatedMovies(apiKey, page = currentPage)
                else -> throw IllegalArgumentException("Invalid category") // Handle invalid category case
            }
            val movies = response.results ?: emptyList() // Extract movie list, or return an empty list if null
            // Return a successful page result with data, previous key, and next key
            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1, // No previous page if on page 1
                nextKey = if (movies.isEmpty()) null else currentPage + 1 // No next page if the list is empty
            )
        } catch (e: Exception) {
            LoadResult.Error(e) // Handle any errors (e.g., network failures)
        }
    }

    // Determines the key for refreshing data when the user swipes to refresh
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
