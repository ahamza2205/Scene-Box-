package com.example.scenebox.screens.movies.domain

interface MovieRepository {
    suspend fun getNowPlayingMovies(apiKey: String): List<Movie>
    suspend fun getPopularMovies(apiKey: String): List<Movie>
    suspend fun getTopRatedMovies(apiKey: String): List<Movie>
    suspend fun getUpcomingMovies(apiKey: String): List<Movie>
}
