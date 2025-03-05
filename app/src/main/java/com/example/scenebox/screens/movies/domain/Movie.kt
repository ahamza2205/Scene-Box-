package com.example.scenebox.screens.movies.domain

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val vote_average: Double,
    val genre_ids: List<Int>
)

data class MovieResponse(
    val results: List<Movie>
)

