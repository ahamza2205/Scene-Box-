package com.example.scenebox.screens.movies.data

import com.example.scenebox.screens.movies.domain.Movie
import com.example.scenebox.data.remote.MovieApiService
import com.example.scenebox.screens.movies.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: MovieApiService) :
    MovieRepository {

    override suspend fun getNowPlayingMovies(apiKey: String): List<Movie> {
        return apiService.getNowPlayingMovies(apiKey).results
    }

    override suspend fun getPopularMovies(apiKey: String): List<Movie> {
        return apiService.getPopularMovies(apiKey).results
    }

    override suspend fun getTopRatedMovies(apiKey: String): List<Movie> {
        return apiService.getTopRatedMovies(apiKey).results
    }

    override suspend fun getUpcomingMovies(apiKey: String): List<Movie> {
        return apiService.getUpcomingMovies(apiKey).results
    }
}
